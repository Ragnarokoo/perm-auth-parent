package com.zhaostudy.system.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhaostudy.common.result.Result;
import com.zhaostudy.common.result.ResultCodeEnum;
import com.zhaostudy.common.utils.JwtHelper;
import com.zhaostudy.common.utils.ResponseUtil;
import com.zhaostudy.model.vo.LoginVo;
import com.zhaostudy.system.custom.CustomUser;
import com.zhaostudy.system.service.LoginLogService;
import com.zhaostudy.system.utils.IpUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/04/6:02 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private RedisTemplate redisTemplate;

    private LoginLogService loginLogService;

    /**
     * 登录令牌过滤器--构造方法
     *
     * @param authenticationManager 身份验证管理器
     */
    public TokenLoginFilter(AuthenticationManager authenticationManager,
                            RedisTemplate redisTemplate,
                            LoginLogService loginLogService) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        //指定登录接口及提交方式，可以指定任意路径
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/system/index/login", "POST"));
        this.redisTemplate = redisTemplate;
        this.loginLogService = loginLogService;
    }

    /**
     * 获取用户名和密码，认证
     *
     * @param request  请求
     * @param response 响应
     * @return {@link Authentication}
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginVo loginVo = new ObjectMapper().readValue(request.getInputStream(), LoginVo.class);
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {

        }
        return null;
    }


    /**
     * 身份验证成功
     *
     * @param request    请求
     * @param response   响应
     * @param chain      链
     * @param authResult 身份验证结果
     * @throws IOException      ioexception
     * @throws ServletException servlet异常
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        // 获取认证对象
        CustomUser customUser = (CustomUser) authResult.getPrincipal();

        // 保存权限数据
        redisTemplate.opsForValue().set(customUser.getUsername(), JSON.toJSONString(customUser.getAuthorities()));

        // 生成token
        String token = JwtHelper.createToken(customUser.getSysUser().getId(), customUser.getSysUser().getUsername());

        // 记录登录日志
        loginLogService.recordLoginLog(customUser.getUsername(),1,
                IpUtil.getIpAddress(request),"登录成功！");
        // 返回
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        ResponseUtil.out(response, Result.ok(map));

    }


    /**
     * 不成功身份验证
     *
     * @param request  请求
     * @param response 响应
     * @param e   失败
     * @throws IOException      ioexception
     * @throws ServletException servlet异常
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {

        if (e.getCause() instanceof RuntimeException) {
            ResponseUtil.out(response, Result.build(null, 204, e.getMessage()));
        } else {
            ResponseUtil.out(response, Result.build(null, ResultCodeEnum.LOGIN_MOBLE_ERROR));
        }

    }

}
