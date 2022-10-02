package com.lisatran.trackmyapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lisatran.trackmyapps.models.Interview;

@Repository
public interface InterviewRepository extends CrudRepository<Interview, Long> {
	
	List<Interview> findAll();
	
	//FIND ALL ORDER BY DATE ASCENDING
	
	@Query(value="SELECT * FROM interviews LEFT JOIN archives ON interviews.id = archives.interview_id WHERE archives.interview_id IS NULL ORDER BY DATE ASC", nativeQuery=true)
	List<Interview>interviewsAsc(); 
}
