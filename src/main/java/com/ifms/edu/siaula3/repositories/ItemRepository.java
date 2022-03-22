package com.ifms.edu.siaula3.repositories;

import com.ifms.edu.siaula3.models.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
