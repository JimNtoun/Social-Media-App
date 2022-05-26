package com.code.social.controller;




import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.social.model.Comment;
import com.code.social.model.Post;
import com.code.social.repository.CommentRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	CommentRepository commentRepository;
	
	@PostMapping("/comments")
	  public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
	    try {
	      Comment _comment = commentRepository.save(new Comment(comment.getTitle(), comment.getBody()));
	      return new ResponseEntity<>(_comment, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	@GetMapping("/comments/{id}")
	  public ResponseEntity<Comment> getCommentById(@PathVariable("id") long id) {
	    Optional<Comment> commentData = commentRepository.findById(id);

	    if (commentData.isPresent()) {
	      return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @PutMapping("/comments/{id}")
	  public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment comment) {
	    Optional<Comment> commentData = commentRepository.findById(id);

	    if (commentData.isPresent()) {
	      Comment _comment = commentData.get();
	      _comment.setTitle(comment.getTitle());
	      _comment.setBody(comment.getBody());
	      return new ResponseEntity<>(commentRepository.save(_comment), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	


	@DeleteMapping("/comments/{id}")
	public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
		try {
			commentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/comments")
	public ResponseEntity<HttpStatus> deleteAllComments() {
		try {
			commentRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

}
	