package cn.fk.te.config;

import cn.fk.common.model.vo.DataBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public DataBox jsonErrorHandler(HttpServletRequest request, Exception e) throws Exception{

        e.printStackTrace();
        return new DataBox("-1","系统异常",e.getMessage());
    }

}
