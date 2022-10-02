package com.lisatran.trackmyapps.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lisatran.trackmyapps.models.Archive;

@Repository
public interface ArchiveRepository extends CrudRepository<Archive, Long>{
	
	List<Archive> findAll();
	
	@Query(value="SELECT * FROM archives JOIN jobs ON archives.job_id = jobs.id ORDER BY DATE DESC", nativeQuery=true)
	List<Archive>archivedJobsDesc();
	
	@Query(value="SELECT * FROM archives JOIN interviews ON archives.interview_id = interviews.id ORDER BY DATE DESC", nativeQuery=true)
	List<Archive>archivedInterviewsDesc();
}
