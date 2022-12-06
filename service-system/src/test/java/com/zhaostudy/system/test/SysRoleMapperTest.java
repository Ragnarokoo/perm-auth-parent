package com.zhaostudy.system.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhaostudy.model.system.SysRole;
import com.zhaostudy.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/10/31/10:58 AM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@SpringBootTest
public class SysRoleMapperTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Test
    public void testDelete() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name","用户管理员");
        sysRoleMapper.delete(wrapper);
    }

    /**
     * 条件查询
     */
    @Test
    public void testSelect() {
        // 创建条件构造器对象
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        // 设置条件
        //wrapper.eq("role_name","用户管理员");
        wrapper.like("role_name","管理员");
        // 调用方法查询
        List<SysRole> list = sysRoleMapper.selectList(wrapper);
        System.out.println("list = " + list);
    }

    /**
     * 测试查询全部
     */
    @Test
    public void findAll() {
        List<SysRole> list = sysRoleMapper.selectList(null);
        for (SysRole sysRole : list) {
            System.out.println("sysRole = " + sysRole);
        }
    }

    /**
     * 测试插入方法
     */
    @Test
    public void insert() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色01");
        sysRole.setRoleCode("testManager");
        sysRole.setDescription("测试角色1");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println("rows = " + rows);
    }

    /**
     * 测试修改方法
     */
    @Test
    public void update() {
        SysRole sysRole = sysRoleMapper.selectById(1);
        sysRole.setDescription("超级管理员");
        sysRoleMapper.updateById(sysRole);
    }

    /**
     * 测试根据id删除
     */
    @Test
    public void delete() {
        int rows = sysRoleMapper.deleteById(1586918357417922563L);
        System.out.println("rows = " + rows);
    }

    /**
     * 测试根据id批量删除
     */
    @Test
    public void testBatchDelete() {
        sysRoleMapper.deleteBatchIds(Arrays.asList(1,2));
    }
}
