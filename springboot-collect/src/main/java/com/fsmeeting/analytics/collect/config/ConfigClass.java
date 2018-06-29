package com.fsmeeting.analytics.collect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:applicationContext-queue.xml"})
public class ConfigClass {

}