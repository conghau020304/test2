package com.hautc.finalproject.config;

import java.util.HashSet;

import com.hautc.finalproject.model.Role;
import com.hautc.finalproject.model.User;
import com.hautc.finalproject.repository.IRoleRepository;
import com.hautc.finalproject.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("dataSeedingListener")
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// Roles
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}
		
		if (roleRepository.findByName("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}
		
		// Admin account
		if (userRepository.findByUsername("hautc@gmail.com") == null) {
			User admin = new User();
			admin.setUsername("hautc@gmail.com");
			admin.setPassword(passwordEncoder.encode("123456"));
			admin.setActive(true);
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			admin.setRoles(roles);
			userRepository.save(admin);
		}
		
		// Member account
		if (userRepository.findByUsername("member@gmail.com") == null) {
			User user = new User();
			user.setUsername("member@gmail.com");
			user.setPassword(passwordEncoder.encode("123456"));
			user.setActive(true);
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_MEMBER"));
			user.setRoles(roles);
			userRepository.save(user);
		}
	}

}
