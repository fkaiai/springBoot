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

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/insertUser",method = RequestMethod.GET)
    //@Transactional
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
        return "testt";
    }
}
