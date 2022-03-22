package com.ifms.edu.siaula3.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.ifms.edu.siaula3.enums.Pools;
import com.ifms.edu.siaula3.models.Item;
import com.ifms.edu.siaula3.repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.validation.BindingResult;

@Controller
public class ItemsController {

    @Autowired
    private ItemRepository itemsRep;
    
    @RequestMapping("/items")
    public String index(Model view) {
        List<Item> itemsList = itemsRep.findAll();
        view.addAttribute("items", itemsList);

        return "items/index";
    }

    @GetMapping("/items/add")
    public String addView(Model view) {
        view.addAttribute("item", new Item());
        view.addAttribute("pools", Pools.values());

        return "items/add";
    }

    @PostMapping("/items/add")
    public String add(@Valid Item item, BindingResult result, Model view) {
        if(result.hasErrors()){
            System.out.println(result.getAllErrors().toString());
            view.addAttribute("pools", Pools.values());
            return "/items/add";
        }

        item.setCreatedAt(new Date());
        itemsRep.save(item);

        return "redirect:/items";
    }
}
