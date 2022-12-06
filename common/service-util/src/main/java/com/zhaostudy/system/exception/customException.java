package com.zhaostudy.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * 自定义异常处理
 * @User：mac
 * @Author: yykk
 * @Date: 2022/10/31/3:14 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class customException extends RuntimeException {

    private Integer code;
    private String msg;
}
