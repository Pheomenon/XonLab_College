package com.xonlab.servicebase.exceptionhandler;

import com.xonlab.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:Gao
 * @Date:2020-04-12 10:19
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("执行全局异常处理");
    }
}
