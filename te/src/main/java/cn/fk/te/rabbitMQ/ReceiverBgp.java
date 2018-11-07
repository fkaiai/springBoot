package cn.fk.te.rabbitMQ;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class ReceiverBgp {

    /**
     * 类型1
     * @param message
     * @throws Exception
     */

    //@RabbitListener(queues = "${spring.bgp_to_cmk_rabbitmq.queueName1}", containerFactory="bgp_to_cmk_factory")
    public void fromBgp1(Message message) throws Exception {

        String messageInfo=new String(message.getBody(),"utf-8");

        Object obj = JSON.parse(messageInfo);
        if (obj instanceof JSONObject) {// 处理消息头或消息尾
            String tag = JSONObject.parseObject(String.valueOf(obj)).getString("tag");
            //开始
            if(tag.equals("start")){
            }else if(tag.equals("end")){}
        } else {// 处理消息主体
            handleBody1((JSONArray) obj);
        }
    }

    private void handleBody1(JSONArray arr) throws Exception {

    }

}
