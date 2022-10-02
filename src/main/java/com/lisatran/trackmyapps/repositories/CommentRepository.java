package com.lisatran.trackmyapps.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lisatran.trackmyapps.models.Comment;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{

	List<Comment> findAll();
	
//	List<Comment> findAllByTest(Job job);
}
