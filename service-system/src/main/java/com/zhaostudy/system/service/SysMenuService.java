package com.zhaostudy.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaostudy.model.system.SysMenu;
import com.zhaostudy.model.vo.AssginMenuVo;
import com.zhaostudy.model.vo.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author yykk
 * @since 2022-11-02
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 菜单列表 (树形)
     *
     * @return {@link List}<{@link SysMenu}>
     */
    List<SysMenu> findNodes();

    /**
     * 删除菜单通过id
     *
     * @param id id
     */
    void removeMenuById(String id);

    /**
     * 更新状态
     *
     * @param id     id
     * @param status 状态
     */
    void updateStatus(String id, Integer status);

    /**
     * 通过角色id找到菜单
     *
     * @param roleId 角色id
     * @return {@link List}<{@link SysMenu}>
     */
    List<SysMenu> findMenuByRoleId(String roleId);

    /**
     * 给角色分配菜单
     *
     * @param assginMenuVo 角色菜单vo实体
     */
    void doAssign(AssginMenuVo assginMenuVo);

    /**
     * 根据用户id获取用户菜单权限值
     *
     * @param userId id
     * @return {@link List}<{@link RouterVo}>
     */
    List<RouterVo> getUserMenuList(String userId);

    /**
     * 根据用户id获取用户按钮权限值
     *
     * @param userId id
     * @return {@link List}<{@link String}>
     */
    List<String> getUserButtonList(String userId);
}
