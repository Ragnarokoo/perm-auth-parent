package com.zhaostudy.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhaostudy.common.result.Result;
import com.zhaostudy.model.system.SysLoginLog;
import com.zhaostudy.model.vo.SysLoginLogQueryVo;
import com.zhaostudy.system.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/05/7:32 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RestController
@RequestMapping(value="/admin/system/sysLoginLog")
public class SysLoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable long page,
                        @PathVariable long limit,
                        SysLoginLogQueryVo sysLoginLogQueryVo) {
        IPage<SysLoginLog> pageModel = loginLogService.selectPage(page,limit,sysLoginLogQueryVo);
        return Result.ok(pageModel);
    }

}
