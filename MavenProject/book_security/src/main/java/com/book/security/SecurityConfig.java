package com.book.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configurable
@EnableWebSecurity//����WebSercurity
//@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/******* �����ڴ���û��洢  ***********/
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER").and()
				.withUser("admin").password("password").roles("USER","ADMIN");
		/*�ȼ���
		auth.inMemoryAuthentication()
			.withUser("user").password("password").authorities("ROLE_USER").and()
			.withUser("admin").password("password").roles("ROLE_USER","ROLE_ADMIN");
		 */
		/******* �������ݿ�������֤  ***********/
/*		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				//��д��֤�ͻ���Ȩ�޵Ĳ�ѯ���
				.usersByUsernameQuery(
						"select username,password,true "+ 
						"from user where username=?")
				.authoritiesByUsernameQuery(
						"select username,'ROLE_USER' from user where username=?")
				//����passwordEncoder()����ָ��һ������ת����
				//�����Զ���һ��passwordEncoder
//				.passwordEncoder(new StandardPasswordEncoder())
				;*/
		/******* ����LDAP������֤  ***********/
/*		auth
			.ldapAuthentication()
				 ���������û�Ӧ������Ϊpeople����֯��Ԫ�����������ǴӸ���ʼ��
				 * ����Ӧ������Ϊgroups����֯��Ԫ������
				 
				.userSearchBase("ou=people")//Ϊ�����û��ṩ�˻�����ѯ
				.userSearchFilter("(uid={0})")
				.groupSearchBase("ou=groups")//Ϊ������֯�ṩ�˻�����ѯ
				.groupSearchFilter("(member={0})")
				
				.contextSource()
					.root("dc:habuma,dc=com")
//					.url("")	//���LDAP����������һ̨������,ͨ���������õ�ַ
				//���ϣ��ͨ������ȶԽ�����֤���������·���ʵ��
				.passwordCompare()
				
				//ָ�����ܲ��ԣ���ҪLDAP��������Ҳ����MD5���м���
				.passwordEncoder(new Md5PasswordEncoder())
				//������뱻�����ڲ�ͬ�������У�����ͨ��passwordAttribute()���������������Ե�����
				.passwordAttribute("passcode")
				;*/
	}
	
	
}
