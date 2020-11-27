package cn.ycc.api.admin.commons.config;

import cn.ycc.api.admin.commons.ext.SysLogConsumer;
import cn.ycc.api.admin.commons.ext.YccSysLogManager;
import cn.ycc.api.admin.mapper.YccSysLogsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.26 21:24
 */
@Configuration
public class BeanConfig {

    @Autowired
    private YccSysLogsMapper sysLogsMapper;

    @Bean
    @ConditionalOnMissingBean
    public SysLogConsumer sysLogConsumer(){
        return (logs)->{
            sysLogsMapper.insert(logs);
        };
    }

    @Bean
    public YccSysLogManager sysLogManager(SysLogConsumer sysLogConsumer){
        return new YccSysLogManager(sysLogConsumer);
    }
}
