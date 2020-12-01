package com.example.springbootelasticsearch.controller;

import com.example.springbootelasticsearch.model.Customer;
import com.example.springbootelasticsearch.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/customers")
    @ResponseBody
    public int saveCustomer(@RequestBody List<Customer> customers) {
        return customerService.saveCustomer(customers);
    }

    @GetMapping(value = "/customers")
    @ResponseBody
    public Iterable<Customer> getAllCustomers() {
         return customerService.getAllCustomers();
    }

    @GetMapping(value = "/customers/{firstName}")
    @ResponseBody
    public Iterable<Customer> getCustomerByFirstName(@PathVariable(value = "firstName") String firstName) {
        return customerService.getCustomerByFirstName(firstName);
    }
}
