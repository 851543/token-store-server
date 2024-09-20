package com.token.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement //开启注解方式的事务管理
@EnableCaching //开启缓存
@EnableScheduling //开启定时任务
@Slf4j //开启日志
public class CommonConfiguration {
}
