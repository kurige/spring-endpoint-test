package com.cg.endpointtest.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequencyRepository extends PagingAndSortingRepository<FreqItem, String> {

    FreqItem findByWord(String word);
}
