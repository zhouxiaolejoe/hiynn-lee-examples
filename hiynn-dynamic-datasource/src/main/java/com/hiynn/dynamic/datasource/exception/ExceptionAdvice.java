package com.hiynn.dynamic.datasource.exception;


import com.hiynn.dynamic.datasource.untils.ResultBuilder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
* @Description  全局异常控制处理器
* @Author ZhouXiaoLe
* @Date  2019/7/17  16:34
* @Param
* @return
**/
@RestControllerAdvice
public class ExceptionAdvice {
    
    /**
    * @Description  捕捉全局参数验证异常
    * @Author ZhouXiaoLe
    * @Date  2019/7/17  23:12
    * @Param [ex]
    * @return com.hiynn.dynamic.datasource.untils.ResultBuilder
    **/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultBuilder handleBindException(MethodArgumentNotValidException ex) {
        return  ResultBuilder.fail(ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
    * @Description  捕捉所有唯一约束异常
    * @Author ZhouXiaoLe
    * @Date  2019/6/25  11:20
    * @Param [e]
    * @return com.hiynn.gybigdata.shiro.util.ResultBuilder
    **/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    public ResultBuilder handle401(DuplicateKeyException e) {
        return ResultBuilder.fail(null,"记录已经存在,违反唯一约束!");
    }

    /**
    * @Description  捕捉其他自定义异常
    * @Author ZhouXiaoLe
    * @Date  2019/7/17  16:34
    * @Param [e]
    * @return com.hiynn.dynamic.datasource.untils.ResultBuilder
    **/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public ResultBuilder handle(CustomException e) {
        return ResultBuilder.fail(null,e.getMessage());
    }

    /**
    * @Description  捕捉404异常
    * @Author ZhouXiaoLe
    * @Date  2019/7/17  16:34
    * @Param [e]
    * @return com.hiynn.dynamic.datasource.untils.ResultBuilder
    **/
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultBuilder handle(NoHandlerFoundException e) {
        return ResultBuilder.fail(null,e.getMessage());
    }

    /**
    * @Description  捕捉所有异常
    * @Author ZhouXiaoLe
    * @Date  2019/7/17  16:35
    * @Param [request, ex]
    * @return com.hiynn.dynamic.datasource.untils.ResultBuilder
    **/
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResultBuilder globalException(HttpServletRequest request, Throwable ex) {
        return ResultBuilder.fail(null,ex.toString() + ": " + ex.getMessage());
    }
}
