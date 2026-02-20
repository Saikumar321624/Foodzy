package com.example.Foodzy.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.example.Foodzy.Dtos.CustomerRegistrationDto;
import com.example.Foodzy.Repositary.CustomerRepo;
import com.example.Foodzy.Repositary.RestaurantRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.Customer;
import com.example.Foodzy.entity.Item;
import com.example.Foodzy.entity.Restaurant;

@Service
public class CustomerService {
	@Autowired
	CustomerRepo cr;
	@Autowired
	RestaurantRepo rr;

	public ResponseStructure<CustomerRegistrationDto> registerCustomer(CustomerRegistrationDto cdto) {
		Customer c=new Customer();
		c.setName(cdto.getName());
		c.setMobileNo(cdto.getMobileNo());
		c.setMail(cdto.getMail());
		c.setGender(cdto.getGender());
		cr.save(c);
		ResponseStructure<CustomerRegistrationDto> resp=new ResponseStructure<CustomerRegistrationDto>();
		resp.setMessage("customer has registred successfully");
		resp.setstatuscode(HttpStatus.CREATED.value());
		resp.setData(cdto);
		return resp;
		
	}

	public ResponseStructure<Customer> find(long mobileNumber) {
		Customer cs=cr.findByMobileNumber(mobileNumber);
		ResponseStructure<Customer>resp=new ResponseStructure<Customer>();
		if (cs!=null) {
			resp.setMessage("customer found successfully");
			resp.setstatuscode(HttpStatus.FOUND.value());
			resp.setData(cs);
		}
		else {
			resp.setMessage("customer not found");
			resp.setstatuscode(HttpStatus.NOT_FOUND.value());
		}
		return resp;
	}

	public ResponseStructure<Customer> delete(long mobileNumber) {
		Customer cs=cr.findByMobileNumber(mobileNumber);
		ResponseStructure<Customer>resp=new ResponseStructure<Customer>();
		if(cs!=null) {
			cr.delete(cs);
			resp.setMessage("deleted successfully");
			resp.setstatuscode(HttpStatus.OK.value());
		}else {
			resp.setMessage("customer not found");
			
		}
		
		return resp;
	}

	public List<Restaurant> searchItem(long customerMobileNo, String searchKey) {
		Customer c=cr.findByMobileNumber(customerMobileNo);
		List<Restaurant> responseList=rr.findAll(searchKey);
		String key = searchKey.toLowerCase();

		List<Restaurant> rList = responseList.stream()
		        .filter(r -> {
		            boolean nameMatch = r.getName().toLowerCase().contains(key);
		            boolean itemMatch = r.getMenu().stream()
		                                 .anyMatch(i -> i.getItemName().toLowerCase().contains(key));

		            return nameMatch || itemMatch;
		        })
		        .toList();
////		List<Restaurant> oList=  responseList.stream().filter(r->r.getName().contains(searchKey)).toList();
//		List<Restaurant> iList = responseList.stream().filter(r -> r.getMenu().stream().filter(i -> i.getItemName().contains(searchKey))).toList();
////		List<Restaurant> rList=responseList.stream().filter(r->(r.getName().toLowerCase().contains(searchKey))||(r.getMenu().stream().anyMatch(i->i.getItemName().toLowerCase().contains(searchKey)))).toList();
		return rList;

	}
	

}

