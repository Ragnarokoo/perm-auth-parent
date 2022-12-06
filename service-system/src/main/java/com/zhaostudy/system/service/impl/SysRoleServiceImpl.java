package com.zhaostudy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaostudy.model.system.SysRole;
import com.zhaostudy.model.system.SysUserRole;
import com.zhaostudy.model.vo.AssginRoleVo;
import com.zhaostudy.model.vo.SysRoleQueryVo;
import com.zhaostudy.system.mapper.SysRoleMapper;
import com.zhaostudy.system.mapper.SysUserRoleMapper;
import com.zhaostudy.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/10/31/11:42 AM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 条件分页查询
     *
     * @param pageParam      页面参数
     * @param sysRoleQueryVo 系统角色查询实体
     * @return {@link IPage}
     */
    @Override
    public IPage selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam,sysRoleQueryVo);
        return pageModel;
    }

    /**
     * 获取用户的角色数据
     *
     * @param userId 用户id
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Override
    public Map<String, Object> getRolesByUserId(String userId) {
        // 获取所有角色
        List<SysRole> roles = baseMapper.selectList(null);
        // 根据用户id查询，已经分配的角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SysUserRole> userRolesList = sysUserRoleMapper.selectList(wrapper);
        // 从userRoles集合中获取所有角色id
        List<String> userRoleIds = new ArrayList<>();
        for (SysUserRole userRole : userRolesList) {
            String roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }
        //创建返回的Map
        Map<String, Object> returnMap = new HashMap<>();
        // 所有角色
        returnMap.put("allRoles",roles);
        // 用户分配角色id集合
        returnMap.put("userRoleIds",userRoleIds);
        return returnMap;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        // 根据用户id删除之前分配角色
        QueryWrapper<SysUserRole> wrap = new QueryWrapper<>();
        wrap.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrap);

        // 获取所有角色id，添加角色用户关系表
        // 角色id列表
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for (String roleId : roleIdList) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleMapper.insert(sysUserRole);
        }
    }
}
