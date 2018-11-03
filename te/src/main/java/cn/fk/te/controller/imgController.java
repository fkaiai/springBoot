package cn.fk.te.controller;

import cn.fk.te.entity.Image;

import cn.fk.te.utils.ValidateCode;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Api(value = "图形")
@RestController
@RequestMapping("/img")
public class imgController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/upload")
    public void addImage(@RequestParam("file") MultipartFile file) {

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

    @GetMapping("/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Session Id: " + request.getRequestedSessionId());
        HttpSession session = request.getSession();

        ValidateCode vCode = new ValidateCode(120,40,5,100);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());

    }

    @GetMapping("/validateCodeCheck")
    public void validateCodeCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Session Id: " + request.getRequestedSessionId());
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute("code");
        //忽略验证码大小写
        if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {
            logger.info("验证码对应不上code=" + code + "  sessionCode=" + sessionCode);
        }else{
            logger.info("验证码对应上code=" + code + "  sessionCode=" + sessionCode);
        }





    }

}
