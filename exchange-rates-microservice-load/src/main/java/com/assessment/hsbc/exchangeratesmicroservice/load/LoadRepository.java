package com.assessment.hsbc.exchangeratesmicroservice.load;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadRepository  extends CrudRepository<LoadEntity, Long> {

}
