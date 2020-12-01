package com.example.springbootelasticsearch.service;

import com.example.springbootelasticsearch.model.Customer;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryDSLService {

    @Autowired
    private ElasticsearchRestTemplate template;

    public List<Customer> searchMultiField(String firstName, Integer age) {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("firstName", firstName))
                .must(QueryBuilders.matchQuery("age", age));

        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();
        SearchHits<Customer> search = template.search(query, Customer.class, IndexCoordinates.of("customer"));

        return search.getSearchHits().stream()
                .map(customerSearchHit -> customerSearchHit.getContent())
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomerSearchData(String input) {
        String searchRegex = ".*" + input + ".*";
        QueryBuilder queryBuilder = QueryBuilders
                .regexpQuery("firstName", searchRegex);

        Query query = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();
        SearchHits<Customer> search = template.search(query, Customer.class, IndexCoordinates.of("customer"));

        return search.getSearchHits().stream()
                .map(customerSearchHit -> customerSearchHit.getContent())
                .collect(Collectors.toList());
    }

    public List<Customer> multiMatchQuery(String text) {
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(text)
                .field("firstName")
                .field("lastName")
                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS);

        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Customer> searchHits = template.search(query, Customer.class, IndexCoordinates.of("customer"));

        return searchHits.getSearchHits().stream()
                .map(customerSearchHit -> customerSearchHit.getContent())
                .collect(Collectors.toList());
    }
}
