package com.example.Foodzy.Repositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foodzy.entity.Restaurant;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

	Restaurant findByMobileno(long phoneNo);	
	List<Restaurant> findByAddress_city(String searchKey);

	Optional<List<Restaurant>> findByAddress_City(String cityname);

}
