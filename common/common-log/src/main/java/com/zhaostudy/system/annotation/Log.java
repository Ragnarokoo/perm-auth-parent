package com.zhaostudy.system.annotation;

import com.zhaostudy.system.enums.BusinessType;
import com.zhaostudy.system.enums.OperatorType;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/05/6:40 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    public boolean isSaveResponseData() default true;
}
