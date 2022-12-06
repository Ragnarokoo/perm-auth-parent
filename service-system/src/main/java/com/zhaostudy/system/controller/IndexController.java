package com.zhaostudy.system.controller;

import com.zhaostudy.common.result.Result;
import com.zhaostudy.common.utils.JwtHelper;
import com.zhaostudy.common.utils.MD5;
import com.zhaostudy.model.system.SysUser;
import com.zhaostudy.model.vo.LoginVo;
import com.zhaostudy.system.exception.customException;
import com.zhaostudy.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/10/31/4:49 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录接口
     * {"code":20000,"data":{"token":"admin-token"}}
     * @return {@link Result}
     */
    @ApiOperation("登录接口测试")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        // 根据username查询数据
        SysUser sysUser = sysUserService.getUserInfoByUserName(loginVo.getUsername());

        // 如果查询为空
        if (sysUser == null) {
            throw new customException(20001,"用户不存在");
        }

        // 潘墩密码是否一致
        String password = loginVo.getPassword();
        String md5Password = MD5.encrypt(password);
        if (!sysUser.getPassword().equals(md5Password)) {
            throw new customException(20001,"密码不正确");
        }

        // 判断用户是否可用
        if (sysUser.getStatus().intValue() == 0) {
            throw new customException(20001,"用户已经被禁用！");
        }

        //  根据userid和username生成token字符串，通过map返回
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());

        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }

    /**
     * 信息接口
     *
     * @return {@link Result}
     * @param request
     */
    @ApiOperation("信息接口测试")
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        // 获取请求头token字符串
        String token = request.getHeader("token");

        // 从token字符串中获取用户名称
        String username = JwtHelper.getUsername(token);

        // 根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }
}
