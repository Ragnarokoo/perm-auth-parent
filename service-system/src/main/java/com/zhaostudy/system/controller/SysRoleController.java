package com.zhaostudy.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaostudy.common.result.Result;
import com.zhaostudy.model.system.SysRole;
import com.zhaostudy.model.vo.AssginRoleVo;
import com.zhaostudy.model.vo.SysRoleQueryVo;
import com.zhaostudy.system.annotation.Log;
import com.zhaostudy.system.enums.BusinessType;
import com.zhaostudy.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/10/31/12:07 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "获取用户的角色列表")
    @GetMapping("toAssign/{userId}")
    public Result toAssign(@PathVariable String userId) {
        Map<String,Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @ApiOperation(value = "用户分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }

    /**
     * 查询所有角色
     *
     * @return {@link Result}
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("查询所有记录")
    @GetMapping("findAll")
    public Result findAllRole() {
        // TODO 模拟异常效果  ArithmeticException
        //try {
        //    int i = 1/0;
        //} catch (Exception e) {
        //    throw new customException(20001,"执行了自定义异常处理");
        //}

        // 调用service
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    /**
     * 逻辑删除接口
     *
     * @param id id
     * @return 是否删除成功！
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result removeRole(@PathVariable Long id) {
        boolean isSuccess = sysRoleService.removeById(id);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 添加角色
     * @RequestBody：不能使用get进行提交，传递的是json格式数据，把json格式数据封装到对象里面 {...}
     * @param sysRole 系统角色
     * @return {@link Result}
     */
    @Log(title = "角色管理",businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result saveRole(@RequestBody SysRole sysRole) {
        boolean isSuccess = sysRoleService.save(sysRole);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 根据id查询角色信息--修改01
     *
     * @param id id
     * @return {@link Result}
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("根据id查询")
    @PostMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    /**
     * 修改角色信息
     *
     * @param sysRole 系统角色
     * @return {@link Result}
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation("修改角色信息")
    @PostMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole) {
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 批量删除
     * json数组格式---java中的list集合
     * @param ids id
     * @return {@link Result}
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids) {
        sysRoleService.removeByIds(ids);
        return Result.ok();
    }

    /**
     * 条件分页查询
     *
     * @param page           页面
     * @param limit          分页显示数量
     * @param sysRoleQueryVo 系统角色查询实体
     * @return {@link Result}
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long limit,
                                    SysRoleQueryVo sysRoleQueryVo) {
        // 创建page对象
        Page<SysRole> pageParam = new Page<>(page, limit);
        // 调用service方法
        IPage pageModel = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        // 返回
        return Result.ok(pageModel);
    }

}
