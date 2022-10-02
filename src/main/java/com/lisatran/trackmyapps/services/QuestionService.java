package com.lisatran.trackmyapps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.lisatran.trackmyapps.models.Question;
import com.lisatran.trackmyapps.repositories.QuestionRepository;

public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public List<Question> allQuestions() {
		return questionRepository.findAll();
	}
	
	// Create question
	public Question createQuestion(Question question) {
		return questionRepository.save(question);
	}
	
	// Get one question
	public Question oneQuestion(Long id) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		if(optionalQuestion.isPresent()) {
			return optionalQuestion.get();
		}else {
			return null;
		}
	}
	
	// Delete question
	public void deleteQuestion(Question question) {
		questionRepository.delete(question);
	}

}
