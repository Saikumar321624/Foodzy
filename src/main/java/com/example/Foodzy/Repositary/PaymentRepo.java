package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Foodzy.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

}
