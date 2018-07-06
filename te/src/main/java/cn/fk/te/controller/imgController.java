package cn.fk.te.controller;

import cn.fk.te.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/img")
public class imgController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/upload")
    public void addImage(@RequestParam("image") MultipartFile file) {



    }

}
