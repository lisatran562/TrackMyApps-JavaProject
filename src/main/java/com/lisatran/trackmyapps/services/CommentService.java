package com.lisatran.trackmyapps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisatran.trackmyapps.models.Comment;

import com.lisatran.trackmyapps.repositories.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository ;
	
	// Find all comments
	public List<Comment> allComments() {
		return commentRepository.findAll();
	}
	
	// Find one comment
	public Comment oneComment(Long id) {
		Optional<Comment> optionalComment = commentRepository.findById(id);
		if(optionalComment.isPresent()) {
			return optionalComment.get();
		}else {
			return null;
		}
	}
	
	// Create
	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	// Update
	public Comment updateComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	// Delete
	public void deleteComment(Comment comment) {
		commentRepository.delete(comment);
	}
}
