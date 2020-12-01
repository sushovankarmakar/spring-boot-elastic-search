package com.example.springbootelasticsearch.controller;

import com.example.springbootelasticsearch.model.Customer;
import com.example.springbootelasticsearch.service.QueryDSLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class QueryDSLController {

    @Autowired
    private QueryDSLService queryDSLService;

    @GetMapping(value = "/search-multi-field/{firstName}/{age}")
    public List<Customer> searchByMultiField(@PathVariable String firstName,
                                             @PathVariable Integer age) {
        return queryDSLService.searchMultiField(firstName, age);
    }

    @GetMapping(value = "/custom-search/{firstName}")
    public List<Customer> getCustomerByField(@PathVariable String firstName) {
        return queryDSLService.getCustomerSearchData(firstName);
    }

    @GetMapping(value = "/multi-match-search/{text}")
    public List<Customer> doMultiMatchQuery(@PathVariable String text) {
        return queryDSLService.multiMatchQuery(text);
    }
}
