package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foodzy.entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{
	

}
