package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foodzy.entity.Payment;
@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

}
