package com.zhaostudy.system.custom;

import com.zhaostudy.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * 自定义密码组件
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/04/5:53 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Component
public class CustomMd5Password implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
