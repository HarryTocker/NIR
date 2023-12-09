package com.find.law.portal.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Конфигурация запуска.
 */
@Configuration
@EnableTransactionManagement
@EnableScheduling
public class BootConfiguration {

}
