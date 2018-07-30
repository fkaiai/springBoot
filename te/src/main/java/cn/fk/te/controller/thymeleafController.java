package cn.fk.te.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class thymeleafController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value="/uploadimg")
    public ModelAndView uploadimg(ModelAndView mv){

        mv.setViewName("/uploadimg.html");
        logger.info("打开链接：/uploadimg.html");
        return mv;

    }

}
