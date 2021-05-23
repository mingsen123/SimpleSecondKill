package com.allen.demo.controller;

import com.allen.demo.error.BusinessException;
import com.allen.demo.error.EmBusinessError;
import com.allen.demo.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author allen
 * @date 2021/5/15 17:29
 */
public class BaseController {
    /**
     * 定义exceptionhandler解决未被controller获取到的异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    //因为导致异常都是业务逻辑异常，所以浏览器都要得到状态码为200
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception e){
        //定义一个Map集合用来存储异常的键值对 其中String为status，Object 为data;
        Map<String,Object> responseData = new HashMap<>();
        //判断获取的异常是否属于自定义异常
        if(e instanceof BusinessException){
            BusinessException businessException = (BusinessException)e;
            responseData.put("errCode",businessException.getErrorCode());
            responseData.put("errMsg",businessException.getErrorMsg());
        }else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrorMsg());
        }
        return CommonReturnType.create(responseData,"fail");
    }
}
