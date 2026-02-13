package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Foodzy.entity.Item;

public interface ItemRepo extends JpaRepository<Item, Long> {

}
