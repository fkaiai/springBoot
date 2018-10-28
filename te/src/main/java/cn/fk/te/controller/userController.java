package cn.fk.te.controller;

import cn.fk.te.entity.User;
import cn.fk.te.service.impl.UserServiceImpl;
import cn.fk.te.utils.DataBox;


import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class userController {

    //Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        System.out.println(12);
        int aa=1/0;
        log.info("a,{}","bb");
        return "success";
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public DataBox get(Long id){
        //if(id==null) return new DataBox("-1","id不能为空");
        return new DataBox("0","查找用户成功",userService.get(id),1);
    }

    @PostMapping(value = "/insert")
    public String insert(@Valid @RequestBody User user){
        userService.insert(user);
        return "success";
    }

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public DataBox selectAll(@RequestHeader HttpHeaders headers, HttpServletResponse response){

        List<User> res=userService.getAll();
        log.debug("查询成功1");
        log.info("查询成功2{}","aaa");



        return new DataBox("0","查找所有用户成功",res,res.size());
    }


    /*public static void main(String[] args) {
        System.out.println(11);
    }*/
}
