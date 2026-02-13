package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foodzy.entity.Orders;
@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

}
