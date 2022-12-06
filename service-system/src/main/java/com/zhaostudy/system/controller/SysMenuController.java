package com.zhaostudy.system.controller;


import com.zhaostudy.common.result.Result;
import com.zhaostudy.model.system.SysMenu;
import com.zhaostudy.model.vo.AssginMenuVo;
import com.zhaostudy.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author yykk
 * @since 2022-11-02
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuMenuService;

    /**
     * 给角色分配菜单
     *
     * @param assginMenuVo assgin菜单签证官
     * @return {@link Result}
     */
    @ApiOperation("给角色分配菜单")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysMenuMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }

    /**
     * 根据角色获取菜单
     *
     * @param roleId 角色id
     * @return {@link Result}
     */
    @ApiOperation("根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<SysMenu> list = sysMenuMenuService.findMenuByRoleId(roleId);
        return Result.ok(list);
    }

    /**
     * 更新状态
     *
     * @param id     id
     * @param status 状态
     * @return {@link Result}
     */
    @ApiOperation(value = "更改菜单状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                               @PathVariable Integer status) {

        sysMenuMenuService.updateStatus(id, status);
        return Result.ok();
    }

    /**
     * 菜单列表（树形）
     *
     * @return {@link Result}
     */
    @ApiOperation("菜单列表")
    @GetMapping("findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuMenuService.findNodes();
        return Result.ok(list);
    }

    /**
     * 添加菜单
     *
     * @param sysMenu 系统菜单
     * @return {@link Result}
     */
    @ApiOperation("添加菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuMenuService.save(sysMenu);
        return Result.ok();
    }

    /**
     * 根据id查询菜单
     *
     * @param id id
     * @return {@link Result}
     */
    @ApiOperation("根据id查询菜单")
    @GetMapping("findNode/{id}")
    public Result findNode(@PathVariable String id) {
        SysMenu sysMenu = sysMenuMenuService.getById(id);
        return Result.ok(sysMenu);
    }

    /**
     * 更新菜单
     *
     * @param sysMenu 系统菜单
     * @return {@link Result}
     */
    @PostMapping("update")
    @ApiOperation("修改菜单")
    public Result update(@RequestBody SysMenu sysMenu) {
        sysMenuMenuService.updateById(sysMenu);
        return Result.ok();
    }

    /**
     * 删除通过id菜单
     *
     * @param id id
     * @return {@link Result}
     */
    @ApiOperation("删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        sysMenuMenuService.removeMenuById(id);
        return Result.ok();
    }
}

