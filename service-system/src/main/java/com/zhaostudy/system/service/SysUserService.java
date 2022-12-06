package com.zhaostudy.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaostudy.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaostudy.model.vo.SysRoleQueryVo;
import com.zhaostudy.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yykk
 * @since 2022-11-01
 */
public interface SysUserService extends IService<SysUser> {


    /**
     * 用户列表
     *
     * @param pageParam      页面参数
     * @param sysUserQueryVo 系统用户查询签证官
     * @return {@link IPage}<{@link SysUser}>
     */
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    /**
     * 更新用户状态
     *
     * @param id     id
     * @param status 状态
     */
    void updateStatus(String id, Integer status);

    /**
     * 根据用户名称进行查询
     *
     * @param username 用户名
     * @return {@link SysUser}
     */
    SysUser getUserInfoByUserName(String username);

    /**
     * 根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
     *
     * @param username 用户名
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> getUserInfo(String username);
}
