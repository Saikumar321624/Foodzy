package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foodzy.entity.CartItem;
@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long>{

}
