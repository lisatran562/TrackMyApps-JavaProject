package com.lisatran.trackmyapps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisatran.trackmyapps.models.Archive;
import com.lisatran.trackmyapps.repositories.ArchiveRepository;

@Service
public class ArchiveService {
	
	@Autowired
	private ArchiveRepository archiveRepository;
	
	public List<Archive> allArchives() {
		return archiveRepository.findAll();
	}
	
	// Create archive
	public Archive createArchive(Archive archive) {
		return archiveRepository.save(archive);
	}
	
	// Get one archive
	public Archive oneArchive(Long id) {
		Optional<Archive> optionalArchive = archiveRepository.findById(id);
		if(optionalArchive.isPresent()) {
			return optionalArchive.get();
		}else {
			return null;
		}
	}
	
	// Create archived job's list
	public List<Archive> createArchivedJobs() {
		return archiveRepository.archivedJobsDesc();
	}
	
	// Create archived interviews's list
	public List<Archive> createArchivedInterviews() {
		return archiveRepository.archivedInterviewsDesc();
	}
	
	// Delete archive
	public void deleteArchive(Archive archive) {
		archiveRepository.delete(archive);
	}
}
