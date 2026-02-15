package com.example.Foodzy.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foodzy.entity.DeliveryPartner;
@Repository
public interface DeliveryPartnerRepo extends JpaRepository<DeliveryPartner,Long> {

	DeliveryPartner findByMobileNumber(long mob);

}
