package com.token.handler;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.token.exception.BaseException;
import com.token.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler(BaseException.class)
    public Result baseExceptionHandler(BaseException e) {
        log.error("异常信息:{}", e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * 捕获实体类字段校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("异常信息:{}", e.getMessage());
        return Result.error(e.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage());
    }

    /**
     * 其它异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("异常信息:{}", e.getMessage());
        return Result.error(e.getMessage());
    }
}