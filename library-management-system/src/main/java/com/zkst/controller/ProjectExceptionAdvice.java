package com.zkst.controller;


import com.zkst.exception.BusinessException;
import com.zkst.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex)
    {
        return new Result(ex.getCode(),null,ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex)
    {
        return new Result(ex.getCode(),null,ex.getMessage());
    }





    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex)
    {
        System.out.println("异常出现");
        return new Result(Code.System_ERR,null,"系统繁忙,请稍后重试");
    }
}
