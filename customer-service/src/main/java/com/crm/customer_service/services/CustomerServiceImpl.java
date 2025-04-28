package com.crm.customer_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.crm.customer_service.exceptions.CustomerAlreadyExistsException;
import com.crm.customer_service.exceptions.CustomerNotFoundException;
import com.crm.customer_service.models.Customer;
import com.crm.customer_service.repositories.CustomerRepository;

import lombok.extern.slf4j.Slf4j;


@Component
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer addCustomer(Customer customer) {
        customerRepository.findByEmail(customer.getEmail()).ifPresent(existingCustomer -> {
            throw new CustomerAlreadyExistsException("Customer already exists with email: " + customer.getEmail());
        });

       

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        
        if(customer.getName() != null) {
            existingCustomer.setName(customer.getName());
        }

        if(customer.getEmail() != null) {
            existingCustomer.setEmail(customer.getEmail());
        }

        if(customer.getPhone() != null) {
            existingCustomer.setPhone(customer.getPhone());
        }

        if(customer.getInteractions() != null) {
            existingCustomer.setInteractions(customer.getInteractions());
        }
        
        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(existingCustomer); 
    }

}

