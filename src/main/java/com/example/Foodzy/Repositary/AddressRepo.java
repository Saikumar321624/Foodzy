package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foodzy.entity.Address;
@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{

}
