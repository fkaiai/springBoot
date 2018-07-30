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

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/upload")
    public void addImage(@RequestParam("image") MultipartFile file) {

        long size = file.getSize();
        logger.info("file.size:"+size);
        String mime = file.getContentType();
        logger.info("file.mime:"+mime);
        String uploadFilePath = file.getOriginalFilename();
        logger.info("uploadFlePath:" + uploadFilePath);
        // 截取上传文件的文件名
        String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1);
        logger.info("multiReq.getFile：" + uploadFileName);
        // 截取上传文件的后缀
        String fileExtName = uploadFilePath.substring(uploadFilePath.lastIndexOf('.') + 1,uploadFilePath.length());
        logger.info("uploadFileSuffix:" + fileExtName);

    }

}
