package com.zhaostudy.system.utils;

import com.zhaostudy.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/02/5:39 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
public class MenuHelpr {


    /**
     * 构建树形结构
     *
     * @param sysMenuList sys menulist
     * @return {@link List}<{@link SysMenu}>
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        // 创建集合封装最终数据
        List<SysMenu> trees = new ArrayList<>();
        // 遍历所有菜单集合
        for (SysMenu sysMenu: sysMenuList) {
            // 找到递归入口，parentId = 0
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 从很节点进行递归查询，查找子节点
     * 判断id = parentid 是否相同，如果相同是子节点，进行数据封装
     *
     * @param sysMenu     系统菜单
     * @param treeNodes
     * @return {@link SysMenu}
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {

        // 数据初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());

        // 遍历递归查找
        for (SysMenu it : treeNodes) {
            // 获取当前菜单id
            //String id = sysMenu.getId();
            //long cid = Long.parseLong(id);
            // 获取所有菜单parentid
            //Long parentId = it.getParentId();
            // 比对
            if (Long.parseLong(sysMenu.getId()) == it.getParentId()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}
