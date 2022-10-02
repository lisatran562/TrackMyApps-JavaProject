package com.lisatran.trackmyapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lisatran.trackmyapps.models.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Long>{
	
	List<Job> findAll();
	
	@Query(value="SELECT * FROM jobs LEFT JOIN archives ON jobs.id = archives.job_id WHERE archives.job_id IS NULL ORDER BY DATE DESC", nativeQuery=true)
	List<Job>jobsDesc();
	// find donations with a certain keyword
//	List<Job> findByDonationNameContaining(String keyword); 
	
	// findAll and order by
//	List<Job> findAllByOrderByDonor();
	
	//find top 3 donations with the most quantity
//	List<Job> findAllByOrderByQuantityDesc();
//	
//	List<Job> findTop3ByOrderByQuantityDesc();
	
	// findAll using native queries
	// 
//	@Query(value="SELECT * FROM JOBS", nativeQuery=true)
//	List<Job>whatever();
	
}
