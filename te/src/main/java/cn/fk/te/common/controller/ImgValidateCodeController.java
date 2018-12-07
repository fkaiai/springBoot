package cn.fk.te.common.controller;

import cn.fk.common.model.vo.DataBox;
import cn.fk.common.util.ValidateCode;
import cn.fk.te.product.model.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/common")
@Api(value = "公共", tags = "公共")
@Slf4j
public class ImgValidateCodeController {

    @GetMapping(value = "/imgValidateCode")
    @ApiOperation(value = "图形验证码",notes = "")
    public void get(HttpServletResponse res) throws IOException {
        ValidateCode vCode = new ValidateCode(100,40,4,9);
        System.err.println(vCode.getCode());
        vCode.write(res.getOutputStream());
    }

}
