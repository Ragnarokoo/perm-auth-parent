package com.zhaostudy.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhaostudy.common.result.Result;
import com.zhaostudy.model.system.SysOperLog;
import com.zhaostudy.model.vo.SysOperLogQueryVo;
import com.zhaostudy.system.service.OperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * @Date: 2022/11/05/7:58 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
@Api(value = "SysOperLog管理", tags = "SysOperLog管理")
@RestController
@RequestMapping(value="/admin/system/sysOperLog")
public class SysOperLogController {

    @Autowired
    private OperLogService operLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "sysOperLogVo", value = "查询对象", required = false)
                    SysOperLogQueryVo sysOperLogQueryVo) {
        IPage<SysOperLog> pageModel = operLogService.selectPage(page,limit,sysOperLogQueryVo);
        return Result.ok(pageModel);
    }
}
