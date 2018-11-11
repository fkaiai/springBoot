/*
package cn.fk.te.controller;

import cn.fk.te.entity.User;
import cn.fk.te.service.impl.UserServiceImpl;
import cn.fk.te.vo.DataBox;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String test(HttpServletRequest request){
        System.out.println("Session Id: " + request.getRequestedSessionId());
        System.out.println(12);
        //int aa=1/0;
        log.info("a,{}","bb");
        return "success";
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public DataBox get(Long id,HttpServletRequest request){
        System.out.println("Session Id: " + request.getRequestedSessionId());
        //if(id==null) return new DataBox("-1","id不能为空");
        return new DataBox("0","查找用户成功",userService.get(id),1);
    }

    @PostMapping(value = "/insert")
    public String insert(@Valid @RequestBody User user){
        userService.insert(user);
        return "success";
    }

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public DataBox selectAll(@RequestHeader HttpHeaders headers, HttpServletRequest request,HttpServletResponse response){
        System.out.println("Session Id: " + request.getRequestedSessionId());
        List<User> res=userService.getAll();
        log.debug("查询成功1");
        log.info("查询成功2{}","aaa");



        return new DataBox("0","查找所有用户成功",res,res.size());
    }


    */
/*public static void main(String[] args) {
        System.out.println(11);
    }*//*

}
*/
