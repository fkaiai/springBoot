package cn.fk.te.controller;

import cn.fk.te.entity.User;
import cn.fk.te.service.UserService;
import cn.fk.te.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/insertUser",method = RequestMethod.GET)
    public String insertUser(){

        User user=new User();
        user.setUsername("abc");
        userService.insert(user);
        Integer aa=4/0;
        userService.insert(user);
        return "success";
    }

    @RequestMapping(value = "/testt",method = RequestMethod.GET)
    public String testt(@RequestHeader HttpHeaders headers){

        Timer timer=new Timer();//实例化Timer类
        timer.schedule(new TimerTask(){
            public void run(){
                System.out.println("退出");
//                this.cancel();
            }
        },4000);//五百毫秒

        return "testt";
    }
}
