package com.zbcn.microauth2auth.security.exception;

import com.zbcn.common.response.ResponseResult;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  全局处理Oauth2抛出的异常
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/24 13:55
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public ResponseResult handleOauth2(OAuth2Exception e) {
        return ResponseResult.fail(e.getMessage());
    }
}
