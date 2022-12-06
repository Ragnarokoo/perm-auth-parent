package com.zhaostudy.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhaostudy.model.system.SysLoginLog;
import com.zhaostudy.model.vo.SysLoginLogQueryVo;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/05/3:39 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
public interface LoginLogService {

    /**
     * 记录登录日志
     *
     * @param username 用户名
     * @param status   状态
     * @param ipaddr   ipaddr
     * @param message  消息
     */
    public void recordLoginLog(String username,Integer status,
                               String ipaddr,String message);

    /**
     * 根据分页查询登录日志
     *
     * @param page               页面
     * @param limit              限制
     * @param sysLoginLogQueryVo 系统登录日志查询签证官
     * @return {@link com.baomidou.mybatisplus.core.metadata.IPage}<{@link SysLoginLog}>
     */
    IPage<SysLoginLog> selectPage(long page, long limit, SysLoginLogQueryVo sysLoginLogQueryVo);
}
