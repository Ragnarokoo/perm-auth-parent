package com.zhaostudy.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaostudy.model.system.SysRoleMenu;
import com.zhaostudy.model.system.SysUser;
import com.zhaostudy.model.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yykk
 * @since 2022-11-01
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {



}
