package com.example.Foodzy.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.example.Foodzy.Dtos.CustomerRegistrationDto;
import com.example.Foodzy.Repositary.CustomerRepo;
import com.example.Foodzy.Response.ResponseStructure;
import com.example.Foodzy.entity.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerRepo cr;

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

	public void searchItem(long customerMobileNo, String searchKey) {
		Customer c=cr.findByMobileNumber(customerMobileNo);
		
	}
	

}

