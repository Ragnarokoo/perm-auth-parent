package com.zhaostudy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaostudy.model.system.SysLoginLog;
import com.zhaostudy.model.vo.SysLoginLogQueryVo;
import com.zhaostudy.system.mapper.LoginLogMapper;
import com.zhaostudy.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/05/3:42 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUsername(username);
        sysLoginLog.setStatus(status);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);
        loginLogMapper.insert(sysLoginLog);
    }

    @Override
    public IPage<SysLoginLog> selectPage(long page, long limit, SysLoginLogQueryVo sysLoginLogQueryVo) {
        // 创建page对象
        Page<SysLoginLog> pageParam = new Page(page, limit);
        // 获取条件值
        String username = sysLoginLogQueryVo.getUsername();
        String createTimeBegin = sysLoginLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysLoginLogQueryVo.getCreateTimeEnd();

        // 封装条件
        QueryWrapper<SysLoginLog> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            wrapper.like("username",username);
        }
        // create_time
        if (!StringUtils.isEmpty(username)) {
            wrapper.ge("create_time",createTimeBegin);
        }
        // updatte_time
        if (!StringUtils.isEmpty(username)) {
            wrapper.le("update_time",createTimeEnd);
        }
        // 调用mapper方法
        IPage<SysLoginLog> pageModel = loginLogMapper.selectPage(pageParam,wrapper);

        return pageModel;
    }
}
