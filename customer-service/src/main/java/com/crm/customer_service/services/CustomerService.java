package com.crm.customer_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

//import com.crm.customer_service.dtos.CustomerDTO;
import com.crm.customer_service.models.Customer;

@Service
public interface CustomerService {
    
        List<Customer> getAllCustomers();
        Customer getCustomerById(Long id);
        Customer addCustomer(Customer customer);
        Customer updateCustomer(Long id, Customer customer);
        void deleteCustomer(Long id);
}