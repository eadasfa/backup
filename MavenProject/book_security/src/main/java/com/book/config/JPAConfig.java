package com.book.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories("com.book")
public class JPAConfig {
	/*配置EntityManager*/
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource ,JpaVendorAdapter jpaVendorAdapter){
		LocalContainerEntityManagerFactoryBean emfb = null;
		try {
			emfb = new LocalContainerEntityManagerFactoryBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		emfb.setPackagesToScan("com.book.entity");
		return emfb;
	}
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
	

		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adapter;
	}
	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceTranslation(){
		return new PersistenceAnnotationBeanPostProcessor();
	}
	//配置以下就可以用JpaRepository
//	@Bean							
//	public DataSourceTransactionManager transactionManager(DataSource dataSource){
//		return new DataSourceTransactionManager(dataSource);
//	}
	/*添加transactionManager,方便使用JpaRespository*/
	@Bean							
	public PlatformTransactionManager transactionManager(EntityManagerFactory em){
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(em);
		return jpaTransactionManager;
	}
}


















