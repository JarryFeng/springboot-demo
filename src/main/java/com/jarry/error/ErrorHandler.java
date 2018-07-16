package com.jarry.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jarry on 2018/7/15.
 *
 * 全局异常捕获
 */
@ControllerAdvice
public class ErrorHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String doExceptionFilter(Exception e){
        e.printStackTrace();
        return "全局异常捕获:"  + e.getMessage();
    }
}
