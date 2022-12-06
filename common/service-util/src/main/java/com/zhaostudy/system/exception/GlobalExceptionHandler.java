package com.zhaostudy.system.exception;

import com.zhaostudy.common.result.Result;
import com.zhaostudy.common.result.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/10/31/3:05 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     *
     * @return {@link Result}
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        System.out.println("全局异常......");
        e.printStackTrace();
        return Result.fail().message("执行类全局异常处理！");
    }

    /**
     * 执行了特定的异常处理
     *
     * @param e e
     * @return {@link Result}
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        System.out.println("特定异常......");
        e.printStackTrace();
        return Result.fail().message("执行了特定的异常处理");
    }

    /**
     * 自定义异常处理
     *
     * @param e e
     * @return {@link Result}
     */
    @ExceptionHandler(customException.class)
    @ResponseBody
    public Result error(customException e) {
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }

    /**
     * spring security异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(AccessDeniedException e) throws AccessDeniedException {
        return Result.fail().code(ResultCodeEnum.PERMISSION.getCode()).message("没有当前功能的操作权限！");
    }
}
