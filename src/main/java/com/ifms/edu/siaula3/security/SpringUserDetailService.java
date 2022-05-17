package com.ifms.edu.siaula3.security;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import com.ifms.edu.siaula3.models.User;
import com.ifms.edu.siaula3.repositories.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SpringUserDetailService implements UserDetailsService {
    private UserRepository userRepository;


    public SpringUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }   

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        org.springframework.security.core.userdetails.User userAuth;

        try{
            System.out.println("Email" + email);
            User user = userRepository.findByEmail(email);

            if(user == null){
                return null;
            }

            Set<GrantedAuthority> roles = getRoles(user);
            userAuth = 
                new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), roles
                );
            return userAuth;
        } catch (Exception error){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }

    private Set<GrantedAuthority> getRoles(User user) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();

        user.getRoles().forEach((role) -> {
            GrantedAuthority auth = new SimpleGrantedAuthority(role.getRole());
            roles.add(auth);
        });

        return roles;
    }
}
