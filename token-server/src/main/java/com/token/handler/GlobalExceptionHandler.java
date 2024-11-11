package com.token.handler;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.token.exception.BaseException;
import com.token.exception.JwtTokenAuthenticationFilterException;
import com.token.exception.JwtTokenInterceptorException;
import com.token.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public Result baseExceptionHandler(BaseException e) {
        log.error("异常信息:{}", e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * 捕获@Validated注解校验异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("异常信息:{}", e.getMessage());
        return Result.error(e.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage());
    }

    /**
     * 捕获Jwt过滤器异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtTokenAuthenticationFilterException.class)
    public Result JwtTokenAuthenticationFilterExceptionHandler(JwtTokenAuthenticationFilterException e) {
        log.error("异常信息:{}", e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * 捕获Jwt拦截器异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtTokenInterceptorException.class)
    public Result JwtTokenInterceptorExceptionHandler(JwtTokenInterceptorException e) {
        log.error("异常信息:{}", e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * 这个异常捕获以后继续向上抛出,让Security处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void AccessDeniedExceptionHandler(AccessDeniedException e) {
        throw e;
    }

    /**
     * 其它异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("异常信息:{}", e.getMessage());
        return Result.error(e.getMessage());
    }
}

