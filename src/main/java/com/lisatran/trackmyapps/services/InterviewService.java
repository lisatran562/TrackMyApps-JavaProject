package com.lisatran.trackmyapps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisatran.trackmyapps.models.Interview;
import com.lisatran.trackmyapps.repositories.InterviewRepository;

@Service
public class InterviewService {
	
	@Autowired
	private InterviewRepository interviewRepository;
	
	// Find all Interviews
		public List<Interview> allInterviews() {
			return interviewRepository.findAll();
		}
		
		// Find one Interview
		public Interview oneInterview(Long id) {
			Optional<Interview> optionalInterview = interviewRepository.findById(id);
			if(optionalInterview.isPresent()) {
				return optionalInterview.get();
			}else {
				return null;
			}
		}
		
		// Create
		public Interview createInterview(Interview interview) {
			return interviewRepository.save(interview);
		}
		
		// Update
		public Interview updateInterview(Interview interview) {
			return interviewRepository.save(interview);
		}
		
		// Delete
		public void deleteInterview(Interview interview) {
			interviewRepository.delete(interview);
		}
		
		// Find all interviews order by date Ascending
		public List<Interview> interviewSort() {
			return interviewRepository.interviewsAsc();
		}
}
