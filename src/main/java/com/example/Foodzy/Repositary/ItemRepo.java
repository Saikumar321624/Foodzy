package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foodzy.entity.Item;
@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

}
