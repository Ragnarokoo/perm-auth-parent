package com.zhaostudy.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaostudy.model.system.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 用户列表
     *
     * @param pageParam      页面参数
     * @param sysUserQueryVo 系统用户查询签证官
     * @return {@link IPage}<{@link SysUser}>
     */
    IPage<SysUser> selectPage(Page<SysUser> pageParam,@Param("vo") SysUserQueryVo sysUserQueryVo);

}
