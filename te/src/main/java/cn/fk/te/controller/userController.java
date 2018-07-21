package cn.fk.te.controller;

import cn.fk.te.entity.User;
import cn.fk.te.service.impl.UserServiceImpl;
import cn.fk.te.utils.DataBox;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {

//    Logger logger = Logger.getLogger(this.getClass());
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/insertUser",method = RequestMethod.GET)
    public String insertUser(){

        User user=new User();
        user.setUsername("abc");
        userService.insert(user);
        return "success";
    }

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public DataBox selectAll(@RequestHeader HttpHeaders headers, HttpServletResponse response,@CookieValue(value="aaa",required=false) String openId){

        System.out.println(openId);
        List<User> res=userService.selectAll();
        logger.debug("查询成功1");
        logger.info("查询成功2{}","aaa");

        //获取微信用户openid存储在cookie中的信息
        Cookie userCookie=new Cookie("openId","aaaaasdfdzvxdasd");
        userCookie.setMaxAge(-1);
        userCookie.setPath("/");
        response.addCookie(userCookie);

        return new DataBox("0","查找所有用户成功",res,res.size());
    }
}
