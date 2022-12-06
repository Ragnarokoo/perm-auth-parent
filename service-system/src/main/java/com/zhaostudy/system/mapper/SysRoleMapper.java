package com.zhaostudy.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaostudy.model.system.SysRole;
import com.zhaostudy.model.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/10/31/10:56 AM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 条件分页查询
     *
     * @param pageParam      页面参数
     * @param sysRoleQueryVo 系统角色查询实体
     * @return {@link IPage}
     */
    IPage selectPage(Page<SysRole> pageParam, @Param("vo") SysRoleQueryVo sysRoleQueryVo);
}
