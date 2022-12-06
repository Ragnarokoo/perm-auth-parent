package com.zhaostudy.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhaostudy.model.system.SysOperLog;
import com.zhaostudy.model.vo.SysOperLogQueryVo;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/05/6:53 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
public interface OperLogService {

    public void saveSysLog(SysOperLog sysOperLog);


    /**
     * 日志条件分页查询
     *
     * @param page              页面
     * @param limit             限制
     * @param sysOperLogQueryVo 系统日志查询签证官歌剧院
     * @return {@link IPage}<{@link SysOperLog}>
     */
    IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);
}
