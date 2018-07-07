package cn.fk.te;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bqjr.mmt.dao.write.WxUserDao;
import com.bqjr.mmt.model.*;
import com.bqjr.mmt.service.WxUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xianlong.liu
 * @date 2018/6/11
 */
@Service
public class WxUserServiceImpl implements WxUserService{
    @Autowired
    private WxUserDao wxUserDao;
    @Autowired
    private RestTemplate restTemplate;
    private static final String ERROR_CODE = "errcode";
    private static final String ERROR_MSG = "errmsg";
    private static final Logger logger = LoggerFactory.getLogger(WxUserServiceImpl.class);
    private static final String REQ_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=10_yEokA7fs15vpdzDUqewv3OxDoo5O3SeeIdocTtn8r4Iy_3QuEQ2RWo1mAdVIdeRJWyX_MvATGbEXG7twrrgN1WfRGLxggn0WmWOYnxHj95Y2fkueXt0Vr7fWmQCs_kAcfYOEH0fIlxg4B5KJKWXjCBAGST";
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(7, 7, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());;
    private static LinkedBlockingQueue<List<String>> blockingQueue = new LinkedBlockingQueue();
    private static AtomicBoolean isStartup = new AtomicBoolean(false);
    private static AtomicInteger total = new AtomicInteger(3000);
    private static AtomicInteger limit = new AtomicInteger(2);
    public static final Integer SIZE = 10000;
    private static final String TOKEN = "11_J6vJAvZVLf_tcElmil6jmVccdq17IThqcxrdQ4O39Fn1P-PKFPgqf91m0c28Q_XG9mX-tAYBuj-Abeb8_co9uP-D5raFmy6Cdh8Mp5Ow8ZXSNdooq5dpNkte8tdhhJRw3dESwnGa2Yyx8s8FJGIkCBAZWY";
    private static final String TEMPLATE_REQ_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + TOKEN;

    private static final String SEND_REQ_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=" + TOKEN;
    private static LinkedBlockingQueue<WechatUserInfo> userBlockingQueue = new LinkedBlockingQueue(4000);

    @Autowired
    private WxUserService wxUserService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer batchInsertWxUserByOpenId(List<String> userList) {
        return wxUserDao.batchInsertWxUserByOpenId(userList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(List<BaseVo> userList) {
        return wxUserDao.update(userList);
    }

    @Override
    public List<BaseVo> queryWxUser(Integer offset, Integer size) {
        return wxUserDao.queryWxUser(offset, size);
    }

    @Override
    public Integer getAllWxUser(){
       return getUser(REQ_URL);
    }

    private Integer getUser(String url) {
        Integer count = 0;
        String result = restTemplate.getForObject(url, String.class);
        WxUserResponse responseVo = JSON.parseObject(result, WxUserResponse.class);
        logger.info(url + "获取总条数:"+ responseVo.getTotal() +" nextOpenid:" + responseVo.getNext_openid());
        if (StringUtils.isBlank(responseVo.getNext_openid())) {
            return 0;
        }
        try {
            blockingQueue.put(responseVo.getOpenid());
        } catch (Exception e){
            logger.error("" , e);
        }

        if (isStartup.compareAndSet(false, true)){
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try{
                            wxUserDao.batchInsertWxUserByOpenId(blockingQueue.take());
                        }catch (Exception e){
                            logger.error("" ,e);
                        }
                    }
                }
            });
        }

        String nextUrl = REQ_URL + "&next_openid=" + responseVo.getNext_openid();
        count += getUser(nextUrl);
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer batchInsertWxUser(List<WechatUserInfo> userList){
        return wxUserDao.batchInsertWxUser(userList);
    }


    @Override
    public void pushTemplate(){
        total.set(0);

        Runnable pushThread = new Runnable(){
            @Override
            public void run() {
                while (true) {
                    int offset = limit.getAndAdd(50);
                    logger.info("开始查询t_wechat_user_info表" + offset + "到" + (offset + SIZE) + "的记录...");
                    List<WechatUserInfo> userInfoList = wxUserDao.queryWechatUserInfo(offset, SIZE);
                    if (userInfoList == null || userInfoList.isEmpty()) {
                        return;
                    }
                    for (WechatUserInfo userInfo : userInfoList){
                        if (total.get() >= 50001){
                            return;
                        }
                        JSONObject reqJsonObject = wrapRequest(userInfo.getUsername(), userInfo.getOpenId());
                        String result = restTemplate.postForObject(TEMPLATE_REQ_URL, reqJsonObject, String.class);
                        JSONObject jsonObject = JSON.parseObject(result);
                        if ("0".equals(jsonObject.getString(ERROR_CODE))) {
                            total.addAndGet(1);
                            logger.info("给第" + total.get() + "个用户[" + userInfo.getUsername() + "："+ userInfo.getOpenId() + "]推送模板消息成功...");
                            wxUserDao.updateWxUser(userInfo);
                        }else {
                            logger.error("错误码："+jsonObject.getString(ERROR_CODE)+"##########推送模板消息异常:" + jsonObject.getString(ERROR_MSG));
                        }
                    }
                }
            };
        };
        threadPool.submit(pushThread);
        threadPool.submit(pushThread);
        threadPool.submit(pushThread);
        threadPool.submit(pushThread);
        threadPool.submit(pushThread);
        threadPool.submit(pushThread);
        threadPool.submit(pushThread);
        threadPool.submit(pushThread);
        threadPool.submit(pushThread);
        threadPool.submit(pushThread);
    }

    private JSONObject wrapRequest(String username, String openId){
        WxTemplateRequestVo requestVo = new WxTemplateRequestVo();
        requestVo.setTouser(openId);
        requestVo.setKeyword1(username);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser",requestVo.getTouser());
        jsonObject.put("template_id",requestVo.getTemplate_id());
        jsonObject.put("url",requestVo.getUrl());
        JSONObject data = new JSONObject();
        Map<String ,String> firstParamMap = new HashMap<>();
        firstParamMap.put("value",requestVo.getFirst());
        firstParamMap.put("color","#173177");
        data.put("first",firstParamMap);
        Map<String ,String> keyword1ParamMap = new HashMap<>();
        keyword1ParamMap.put("value",requestVo.getKeyword1());
        keyword1ParamMap.put("color","#173177");
        data.put("keyword1",keyword1ParamMap);
        Map<String ,String> keyword2ParamMap = new HashMap<>();
        keyword2ParamMap.put("value",requestVo.getKeyword2());
        keyword2ParamMap.put("color","#173177");
        data.put("keyword2",keyword2ParamMap);
        Map<String ,String> remarkParamMap = new HashMap<>();
        remarkParamMap.put("value",requestVo.getRemark());
        remarkParamMap.put("color","#FF0000");
        data.put("remark",remarkParamMap);
        jsonObject.put("data", data);
        return jsonObject;
    }

    @Override
    public List<WechatUserInfo> queryWxSexUser(Integer offset, Integer size){
        return wxUserDao.queryWxSexUser(offset, size);
    }

    @Override
    public Integer batchUpdateWxSexUser(List<WechatUserInfo> userList){
        return wxUserDao.batchUpdateWxSexUser(userList);
    }



    @Override
    public void pushSexUser(){
        threadPool.execute(new SexTagThread());
        threadPool.execute(new SexTagThread());
        threadPool.execute(new SexTagThread());
        threadPool.execute(new SexTagThread());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RestTemplate restTemplate = new RestTemplate();
                List<WechatUserInfo> userInfoList = new ArrayList<>();
                while (true){
                    if (threadPool.getActiveCount() <= 1  && userBlockingQueue.size() == 0){
                        return;
                    }
                    try {
                        for (int i = 0; i < 3; i++) {
                            WechatUserInfo wechatUserInfo = userBlockingQueue.take();
                            userInfoList.add(wechatUserInfo);
                        }
                        String result = restTemplate.postForObject(SEND_REQ_URL, wrapPushRequest(userInfoList), String.class);
                        logger.info("#############"+ result);
                        JSONObject jsonObject = JSON.parseObject(result);
                        if ("0".equals(jsonObject.getString(ERROR_CODE))) {
                            wxUserService.batchUpdateWxSexUser(userInfoList);
                        }
                    }catch (Exception e){
                        logger.error("", e);
                    }
                }
            }
        };

        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
    }

    class SexTagThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                int offset = limit.getAndAdd(SIZE);
                logger.info("开始查询" + offset + "到" + (offset + SIZE) + "的记录...");
                List<WechatUserInfo> userInfoList = wxUserService.queryWxSexUser(offset, SIZE);
                if (userInfoList == null || userInfoList.isEmpty()) {
                    return;
                }

                String result = restTemplate.postForObject(SEND_REQ_URL, wrapPushRequest(userInfoList), String.class);
                logger.info("#############"+ result);
                JSONObject jsonObject = JSON.parseObject(result);
                if ("0".equals(jsonObject.getString(ERROR_CODE))) {
                    wxUserService.batchUpdateWxSexUser(userInfoList);
                    logger.info(offset + "到" + (offset + SIZE) + "的记录用户推送成功...");
                } else {
                    logger.error("错误码：" + jsonObject.getString(ERROR_CODE) + "##########" + offset + "到" + (offset + SIZE) + "的记录用户分组异常:" + jsonObject.getString(ERROR_MSG));
                    try {
                        for (WechatUserInfo wechatUserInfo : userInfoList) {
                            logger.error("%%%%%%%%" + wechatUserInfo.getId());
                            userBlockingQueue.put(wechatUserInfo);
                        }
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                }

                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    private JSONObject wrapPushRequest(List<WechatUserInfo> userInfoList){
        JSONObject jsonObject = new JSONObject();
        List<String> userList = new ArrayList<>();
        for (WechatUserInfo userInfo : userInfoList){
            if ("2".equals(userInfo.getSex())){
                userList.add(userInfo.getOpenId().trim());
            }
        }
        jsonObject.put("touser",userList);
        JSONObject mpnews = new JSONObject();
        mpnews.put("media_id","knYX2XRIyxwICik7JGCsFiqfKeI6BtTwsr7TcrKUegs"); // 女性
        jsonObject.put("mpnews",mpnews);
        jsonObject.put("msgtype","mpnews");
        jsonObject.put("send_ignore_reprint",0);
        return jsonObject;
    }

}
