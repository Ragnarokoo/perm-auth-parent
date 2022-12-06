package com.zhaostudy.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhaostudy.model.system.SysLoginLog;
import com.zhaostudy.model.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * 登录日志
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/02/3:51 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Repository
@Mapper
public interface OperLogMapper extends BaseMapper<SysOperLog> {
}
