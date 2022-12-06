package com.zhaostudy.system.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhaostudy.model.system.SysRole;
import com.zhaostudy.system.mapper.SysRoleMapper;
import com.zhaostudy.system.service.SysRoleService;
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
public class SysRoleServiceTest {

    /**
     * 注入service
     */
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询所有数据
     */
    @Test
    public void findAll() {
        // service方法实现
        List<SysRole> list = sysRoleService.list();
        System.out.println("list = " + list);
    }

    /**
     * 插入操作
     */
    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员111");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");
        sysRoleService.save(sysRole);
    }

    /**
     * 更新操作
     */
    @Test
    public void update() {
        SysRole sysRole = sysRoleService.getById(8);
        sysRole.setDescription("test");
        sysRoleService.updateById(sysRole);
    }

    /**
     * 删除操作
     */
    @Test
    public void remove() {
        sysRoleService.removeById(8);
    }

    /**
     * 根据条件查询
     */
    @Test
    public void select() {
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("role_code","SYSTEM");
        List<SysRole> list = sysRoleService.list(sysRoleQueryWrapper);
        System.out.println(list);
    }

}
