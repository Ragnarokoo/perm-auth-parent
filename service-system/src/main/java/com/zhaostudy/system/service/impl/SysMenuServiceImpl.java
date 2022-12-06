package com.zhaostudy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhaostudy.model.system.SysMenu;
import com.zhaostudy.model.system.SysRoleMenu;
import com.zhaostudy.model.vo.AssginMenuVo;
import com.zhaostudy.model.vo.RouterVo;
import com.zhaostudy.system.exception.customException;
import com.zhaostudy.system.mapper.SysMenuMapper;
import com.zhaostudy.system.mapper.SysRoleMenuMapper;
import com.zhaostudy.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaostudy.system.utils.MenuHelpr;
import com.zhaostudy.system.utils.RouterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author yykk
 * @since 2022-11-02
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        // 获取所有菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        // 所有菜单数据转换成要求的数据格式
        return MenuHelpr.buildTree(sysMenuList);
    }

    @Override
    public void removeMenuById(String id) {
        // 查询当前菜单下面是否有子菜单
        // 根据id = parentId
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysMenu::getParentId, id);
        //wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        // count > 0 有子菜单
        if (count > 0) {
            throw new customException(201, "请先删除子菜单");
        }
        // 调用删除
        baseMapper.deleteById(id);
    }

    @Override
    public void updateStatus(String id, Integer status) {
        // 根据用户id查询
        SysMenu sysMenu = baseMapper.selectById(id);
        // 设置修改状态
        sysMenu.setStatus(status);
        // 调用方法修改
        baseMapper.updateById(sysMenu);
    }

    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {
        // 获取所有菜单 status = 1
        QueryWrapper<SysMenu> wrapperMenu = new QueryWrapper<>();
        wrapperMenu.eq("status", 1);
        List<SysMenu> menuList = baseMapper.selectList(wrapperMenu);

        // 根据角色id查询 角色分配过的菜单列表
        QueryWrapper<SysRoleMenu> wrapperRoleMenu = new QueryWrapper<>();
        wrapperRoleMenu.eq("role_id", roleId);
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(wrapperRoleMenu);

        // 从第二步查询列表中，获取角色分配所有菜单id
        List<String> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu sysRoleMenu : roleMenus) {
            String menuId = sysRoleMenu.getMenuId();
            roleMenuIds.add(menuId);
        }

        // 数据处理： isSelect 如果为菜单选中 true，否则为false！
        // 拿着分配菜单id 和 所有菜单进行比对，有相同的，让isSelect值为true
        for (SysMenu sysMenu : menuList) {
            if (roleMenuIds.contains(sysMenu.getId())) {
                sysMenu.setSelect(true);
            } else {
                sysMenu.setSelect(false);
            }
        }

        // 转换成属性结构为了最终显示 MenuHelper 方法实现！
        return MenuHelpr.buildTree(menuList);
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        // 根据角色id删除菜单权限
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);

        // 遍历菜单id列表，一个一个进行添加
        List<String> menuIdList = assginMenuVo.getMenuIdList();
        for (String menuId : menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }

    @Override
    public List<RouterVo> getUserMenuList(String userId) {
        // admin 是超级管理员，操作所有内容
        List<SysMenu> sysMenuList = null;
        // 判断userid值是1代表超级管理员，查询所有权限数据
        if ("1".equals(userId)) {
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("status", 1);
            wrapper.orderByAsc("sort_value");
            sysMenuList = baseMapper.selectList(wrapper);
        } else {
            // 判断userid不是1，其他类型用户，查询这个用户权限
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }

        // 构建树形结构
        List<SysMenu> sysMenuTreeList = MenuHelpr.buildTree(sysMenuList);

        // 转换成前端路由要求的格式数据

        return RouterHelper.buildRouters(sysMenuTreeList);
    }

    @Override
    public List<String> getUserButtonList(String userId) {
        List<SysMenu> sysMenuList = null;
        // 判断是否是管理员
        if ("1".equals(userId)) {
            sysMenuList = baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        } else {
            sysMenuList = baseMapper.findMenuListUserId(userId);
        }
        // sysMenuList遍历
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            // type = 2
            if (sysMenu.getType() == 2) {
                String perms = sysMenu.getPerms();
                permissionList.add(perms);
            }
        }
        return permissionList;
    }
}
