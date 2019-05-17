package spittr.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import spittr.dao.SpitterRepository;
import spittr.service.SpitterService;
import spittr.service.SpitterUserService;
import sun.print.resources.serviceui;

@Configurable
@EnableWebSecurity//启用WebSercurity
//@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	@Autowired
	@Qualifier("spitterService")
	private SpitterService spitterSercice;
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		/**
		 * authenticated()要求在执行该请求时必须已经登录了应用
		 * permitAll()没限制
		 * hasAuthority("ROLE_SPITTER")与hasRole("SPITTER")要求登录用户拥有SPITTER权限
		 * 
		 */
//		http.csrf().disable();
		http
			.formLogin()//启用默认登录页
			.and()
			.authorizeRequests()//调用该方法返回的对象的方法来配置请求级别的安全性细节
				//第一次调用antMatchers()指定"/spitters/me"路径需要进行认证
				.antMatchers("/spitter/**").authenticated()
				//第二次调用antMatchers()指定"spittles"路径POST请求需要进行认证
				.antMatchers(HttpMethod.POST,"spittles").hasAuthority("ROLE_SPITTER")
														//hasRole("SPITTER")
				//其他所有的请求都是允许的不需要认证
				.anyRequest().permitAll();
/*				.and()
				.requiresChannel()
					//不论何时，只要是对"/spitter/form"的请求，spring security都视为安全通道
					.antMatchers("/spitter/form").requiresSecure();//需要HTTPS
					*/
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//将userDetailsService设置到安全配置中
		//自定义
		auth
			.userDetailsService(new SpitterUserService(spitterSercice));
		
		/******* 基于内存的用户存储  ***********/
//		auth
//			.inMemoryAuthentication()
//				.withUser("user").password("password").roles("USER").and()
//				.withUser("admin").password("password").roles("USER","ADMIN");
		/*等价于
		auth.inMemoryAuthentication()
			.withUser("user").password("password").authorities("ROLE_USER").and()
			.withUser("admin").password("password").roles("ROLE_USER","ROLE_ADMIN");
		 */
		/******* 基于数据库表进行认证  ***********/
/*		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				//重写认证和基本权限的查询语句
				.usersByUsernameQuery(
						"select username,password,true "+ 
						"from user where username=?")
				.authoritiesByUsernameQuery(
						"select username,'ROLE_USER' from user where username=?")
				//借助passwordEncoder()方法指定一个密码转码器
				//可以自定义一个passwordEncoder
//				.passwordEncoder(new StandardPasswordEncoder())
				;*/
		/******* 基于LDAP进行认证  ***********/
/*		auth
			.ldapAuthentication()
				 我们声明用户应该在名为people的组织弹元下搜索而不是从根开始。
				 * 而组应该在名为groups的组织单元下搜索
				 
				.userSearchBase("ou=people")//为查找用户提供了基础查询
				.userSearchFilter("(uid={0})")
				.groupSearchBase("ou=groups")//为查找组织提供了基础查询
				.groupSearchFilter("(member={0})")
				
				.contextSource()
					.root("dc:habuma,dc=com")
//					.url("")	//如果LDAP服务器在另一台机器上,通过以下配置地址
				//如果希望通过密码比对进行认证，声明以下方法实现
				.passwordCompare()
				
				//指定加密策略，需要LDAP服务器上也是用MD5进行加密
				.passwordEncoder(new Md5PasswordEncoder())
				//如果密码被保存在不同的属性中，可以通过passwordAttribute()方法声明密码属性的名称
				.passwordAttribute("passcode")
				;*/
	}
	
	
}
