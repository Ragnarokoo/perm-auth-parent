package com.zhaostudy.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaostudy.common.result.Result;
import com.zhaostudy.common.utils.MD5;
import com.zhaostudy.model.system.SysUser;
import com.zhaostudy.model.vo.SysRoleQueryVo;
import com.zhaostudy.model.vo.SysUserQueryVo;
import com.zhaostudy.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yykk
 * @since 2022-11-01
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "更改用户状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                               @PathVariable Integer status) {

        sysUserService.updateStatus(id, status);
        return Result.ok();
    }

    @ApiOperation(value = "用户列表数据")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       SysUserQueryVo sysUserQueryVo) {

        // 创建page对象
        Page<SysUser> pageParam = new Page<>(page, limit);
        // 调用service方法
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam,sysUserQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser) {
        // 把输入密码进行加密 MD5
        String encrypt = MD5.encrypt(sysUser.getPassword());
        sysUser.setPassword(encrypt);
        boolean isSuccess = sysUserService.save(sysUser);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("getUser/{id}")
    public Result getUser(@PathVariable("id") String id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser) {
        boolean isSuccess = sysUserService.updateById(sysUser);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable("id") String id) {
        boolean isSuccess = sysUserService.removeById(id);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

}

