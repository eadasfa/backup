package com.book.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.book.service","com.book.dao"})
public class ServiceConfig {

}
