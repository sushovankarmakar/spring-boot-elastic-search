package com.example.springbootelasticsearch.repository;

import com.example.springbootelasticsearch.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

    List<Customer> findByFirstName(String firstName);

}
