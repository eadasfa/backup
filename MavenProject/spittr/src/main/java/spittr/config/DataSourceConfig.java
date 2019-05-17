package spittr.config;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import spittr.service.SqlHelper;

@Configuration
public class DataSourceConfig {
	private static String ip="127.0.0.1";
	private static int port=3306;
	private static String dbname="spittr";
	private static String DriverClassName="com.mysql.jdbc.Driver";
	private static String username="root";
	private static String password="123456";
	private static String URL = "jdbc:mysql://"+ip+":"+port+"/"+dbname+"?userUnicode=true&characterEncoding=UTF-8";
	@Bean
	public DataSource qaData(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DriverClassName);
		ds.setUrl(URL);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}
//	@Bean
//	public DataSource jdbcData(){
//		DriverManagerDataSource ds = new DriverManagerDataSource();
//		ds.setDriverClassName(DriverClassName);
//		ds.setUrl(URL);
//		ds.setUsername(username);
//		ds.setPassword("123456");
//		return ds;
//	}
	@Bean
	public SqlHelper helper( ){
		return new SqlHelper() ;
	}
	//jdbcTemplate
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
}
