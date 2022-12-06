package com.zhaostudy.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaostudy.model.system.SysOperLog;
import com.zhaostudy.model.vo.SysLoginLogQueryVo;
import com.zhaostudy.model.vo.SysOperLogQueryVo;
import com.zhaostudy.system.mapper.OperLogMapper;
import com.zhaostudy.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/05/6:55 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Service
public class OperServiceImpl implements OperLogService {


    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        operLogMapper.insert(sysOperLog);
    }

    @Override
    public IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo) {
        Page<SysOperLog> pageParams = new Page<>();
        // 获取条件值
        String title = sysOperLogQueryVo.getTitle();
        String operName = sysOperLogQueryVo.getOperName();
        String createTimeBegin = sysOperLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysOperLogQueryVo.getCreateTimeEnd();
        // 封装参数
        QueryWrapper<SysOperLog> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(operName)) {
            wrapper.like("oper_name",operName);
        }
        if (!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time",createTimeBegin);
        }
        if (!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le("update_time",createTimeEnd);
        }
        // 调用mapper方法实现条件分页查询
        IPage<SysOperLog> sysOperLogPage = operLogMapper.selectPage(pageParams, wrapper);
        return sysOperLogPage;
    }
}
