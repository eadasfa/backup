package spittr.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import spittr.entity.Spitter;

public class SpitterUserService implements UserDetailsService{
	
	private final SpitterService spitterService;
	public SpitterUserService(SpitterService spitterService){
		this.spitterService = spitterService;
	}
	public UserDetails loadUserByUsername(String username) 
		throws UsernameNotFoundException{
		//查找Spitter
		Spitter spitter =null;
		try {
			spitter = spitterService.findByUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(spitter);
		if(spitter != null){
			//创建权限列表
			List<GrantedAuthority> authorities = 
					new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));
			return new User(spitter.getUserName(),spitter.getPassword(),authorities);
		}
		throw new UsernameNotFoundException(
				"User '"+username+"' not found.");
	}

}
