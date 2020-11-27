package cn.ycc.api.admin.commons.ext;

import cn.ycc.api.admin.entity.YccSysLogs;

import java.util.function.Consumer;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.26 21:05
 */
public interface SysLogConsumer extends Consumer<YccSysLogs> {
}
