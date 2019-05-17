package com.book.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
	private static String ip="127.0.0.1";
	private static int port=3306;
	private static String dbname="bookdb";
	private static String driverClassName="com.mysql.jdbc.Driver";
	private static String username="root";
	private static String password="123456";
	private static String URL = "jdbc:mysql://"+ip+":"+port+"/"+dbname+"?userUnicode=true&characterEncoding=UTF-8";
	@Bean
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(URL);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
}
