package com.example.springbootelasticsearch.service;

import com.example.springbootelasticsearch.model.Customer;
import com.example.springbootelasticsearch.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public int saveCustomer(List<Customer> customers) {
        customers = (List<Customer>) customerRepository.saveAll(customers);
        return customers.size();
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Iterable<Customer> getCustomerByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

}
