package com.lisatran.trackmyapps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisatran.trackmyapps.models.Job;
import com.lisatran.trackmyapps.models.User;
import com.lisatran.trackmyapps.repositories.JobRepository;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	
	// Find all jobs
	public List<Job> allJobs() {
		return jobRepository.findAll();
	}
	
	
	// Find one
	public Job oneJob(Long id) {
		Optional<Job> optionalJob = jobRepository.findById(id);
		if(optionalJob.isPresent()) {
			return optionalJob.get();
		}else {
			return null;
		}
	}
	
	// Create
	public Job createJob(Job job) {
		return jobRepository.save(job);
	}
	
	// Update
	public Job updateJob(Job job) {
		return jobRepository.save(job);
	}
	
	// Delete
	public void deleteJob(Job job) {
		jobRepository.delete(job);
	}
	
	// Favorite a job
	public Job favoriteJob(Long jobId, User user) {
		Job job = this.oneJob(jobId);
		job.getUserFavorites().add(user);
		jobRepository.save(job);
		return jobRepository.save(job);
	}
	
	// Unfavorite a job
	public Job unfavoriteJob(Long jobId, User user) {
		Job job = this.oneJob(jobId);
		job.getUserFavorites().remove(user);
		return jobRepository.save(job);
	}
	
	
	// Find all jobs order applied date in descending
	public List<Job> jobSort() {
		return jobRepository.jobsDesc();
	}
	
	// Test queries
//	public List<Job> testQuery(String keyword) {
//		return jobRepository.findByDonationNameContaining(keyword);
//		return jobRepository.findAllByOrderByDonor();
//		return jobRepository.findAllByOrderByQuantityDesc();
//		return jobRepository.findTop3ByOrderByQuantityDesc();
//		return jobRepository.whatever();
//	}
}
