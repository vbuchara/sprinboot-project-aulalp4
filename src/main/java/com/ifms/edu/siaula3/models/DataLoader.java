package com.ifms.edu.siaula3.models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ifms.edu.siaula3.repositories.RoleRepository;
import com.ifms.edu.siaula3.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private RoleRepository roleRep;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public void run(String... args) throws Exception {
		Role roleAdmin = roleRep.findByRole("ADMIN");
		Role roleUser = roleRep.findByRole("USER");
		if (roleAdmin == null) {
			roleRep.save(new Role("ADMIN"));
		}
		if (roleUser == null) {
			roleRep.save(new Role("USER"));
		}
		roleAdmin = roleRep.findByRole("ADMIN");
		roleUser = roleRep.findByRole("USER");
		
		if (userRep.findByEmail("vbuchara31@gmail.com") == null){
			User user = new User(
                "Vinicius",
                "07481812101",
                new Date(), 
                "vbuchara31@gmail.com",
                passwordEncoder.encode("123"),
                0.0
            );
			List<Role> userRoles = Arrays.asList(roleAdmin, roleUser);
			user.setRoles(userRoles);
			userRep.save(user);
		} 
	}

}
