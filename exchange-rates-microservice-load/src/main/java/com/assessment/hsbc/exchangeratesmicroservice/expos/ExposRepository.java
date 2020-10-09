package com.assessment.hsbc.exchangeratesmicroservice.expos;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assessment.hsbc.exchangeratesmicroservice.load.LoadEntity;

@Repository
public interface ExposRepository  extends CrudRepository<LoadEntity, Long> {

	@Query("select t from LoadEntity t where t.date = ?1")
	LoadEntity findByDate(Date date);
	
	@Query("select t from LoadEntity t where  t.date >= ?1 AND t.date <= ?2")
	Iterable<LoadEntity> findByDate(Date frmDate, Date toDate);

}
