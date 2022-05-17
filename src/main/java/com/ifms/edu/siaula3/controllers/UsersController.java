package com.ifms.edu.siaula3.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.ifms.edu.siaula3.models.Role;
import com.ifms.edu.siaula3.models.User;
import com.ifms.edu.siaula3.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsersController extends BaseController {

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String index(Model view) {
        List<User> usersList = userRep.findAll();

        view.addAttribute("users", usersList);

        return "users/index";
    }

    @GetMapping("/users/add")
    public String addView(Model view) {
        List<Role> roles = roleRepository.findAll();

        view.addAttribute("user", new User());
        view.addAttribute("roles", roles);

        return "users/add";
    }

    @PostMapping("/users/add")
    public String add(
        @Valid User user,
        BindingResult result, 
        Model view,
        @RequestParam("roles") List<Long> roles
    ) {
        List<Role> allRoles = roleRepository.findAll();

        if(result.hasErrors()){
            view.addAttribute("user", user);
            view.addAttribute("roles", allRoles);

            return "users/add";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        roles.forEach((id) -> {
            Optional<Role> role = roleRepository.findById(
                Long.valueOf(id)
            );
            System.out.println(id);
            if(role.isPresent()){
                user.getRoles().add(role.get());
            }
        });
        userRep.save(user);

        return "redirect:/users";
    }

    @DeleteMapping("/users/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable String id){
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            userRep.deleteById(Long.valueOf(id));
            response.put("mensagem", "Usuário de " + id + " deletado com sucesso.");
        } catch(Exception e) {
            response.put("mensagem", "Erro ao deletar usuário.");
        }

        return response;
    }
}
