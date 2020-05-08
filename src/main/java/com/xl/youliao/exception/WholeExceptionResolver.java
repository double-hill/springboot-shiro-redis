package com.xl.youliao.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author WeiC
 * @date 2020/5/7 16:41
 */
@Slf4j
@ControllerAdvice
public class WholeExceptionResolver {
    @ExceptionHandler(UnauthorizedException.class)
    public void calUnauthorizedException(UnauthorizedException e){
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = requestAttributes.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("{\"code\":403,\"msg\":\"无权访问\"}");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @ExceptionHandler(Exception.class)
    public void calException(Exception e){
        log.error("Whole Exception:",e);
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = requestAttributes.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("{\"code\":500,\"msg\":\"系统异常\"}");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
