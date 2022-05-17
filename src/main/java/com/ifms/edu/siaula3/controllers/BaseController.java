package com.ifms.edu.siaula3.controllers;

import com.ifms.edu.siaula3.models.User;
import com.ifms.edu.siaula3.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {

    @Autowired
    protected UserRepository userRep;
    
    @ModelAttribute("userLogged")
    public User getUserLogged() {
        Object objectLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        if(objectLogged instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User principalLogged = 
                (org.springframework.security.core.userdetails.User) objectLogged;
            
            User userLogged = userRep.findByEmail(principalLogged.getUsername());
    
            return userLogged;
        }

        return null;
    }
}
