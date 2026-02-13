package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Foodzy.entity.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long>{
	

}
