package com.lisatran.trackmyapps.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lisatran.trackmyapps.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{
	
	List<Question> findAll();

}
