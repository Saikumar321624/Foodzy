package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Foodzy.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

}
