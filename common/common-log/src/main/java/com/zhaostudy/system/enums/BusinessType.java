package com.zhaostudy.system.enums;

/**
 * Created with IntelliJ IDEA.
 * 业务操作类型
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/05/6:44 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
public enum BusinessType {

    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    ASSGIN,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 更新状态
     */
    STATUS,

    /**
     * 清空数据
     */
    CLEAN,
}
