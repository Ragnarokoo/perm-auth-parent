package com.zhaostudy.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaostudy.model.system.SysRole;
import com.zhaostudy.model.vo.AssginRoleVo;
import com.zhaostudy.model.vo.SysRoleQueryVo;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/10/31/11:42 AM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 条件分页查询
     * @param pageParam
     * @param sysRoleQueryVo
     * @return
     */
    IPage selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

    /**
     * 获取用户的角色数据
     *
     * @param userId 用户id
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> getRolesByUserId(String userId);

    /**
     * 给用户分配角色
     *
     * @param assginRoleVo 签证官assgin作用
     */
    void doAssign(AssginRoleVo assginRoleVo);
}
