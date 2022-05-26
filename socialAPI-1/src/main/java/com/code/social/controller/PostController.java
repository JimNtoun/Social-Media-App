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

import com.code.social.model.Post;
import com.code.social.repository.PostRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	PostRepository postRepository;

	@PostMapping("/posts")
	  public ResponseEntity<Post> createPost(@RequestBody Post post) {
	    try {
	    	Post _post = postRepository.save(new Post(post.getTitle(), post.getDescription(), false));
	      return new ResponseEntity<>(_post, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@GetMapping("/posts/{id}")
	  public ResponseEntity<Post> getPostById(@PathVariable("id") long id) {
	    Optional<Post> postData = postRepository.findById(id);

	    if (postData.isPresent()) {
	      return new ResponseEntity<>(postData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PutMapping("/posts/{id}")
	  public ResponseEntity<Post> updatePost(@PathVariable("id") long id, @RequestBody Post post) {
	    Optional<Post> postData = postRepository.findById(id);

	    if (postData.isPresent()) {
	      Post _post = postData.get();
	      _post.setTitle(post.getTitle());
	      _post.setDescription(post.getDescription());
	      _post.setPublished(post.isPublished());
	      return new ResponseEntity<>(postRepository.save(_post), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	
	

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") long id) {
		try {
			postRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/posts")
	public ResponseEntity<HttpStatus> deleteAllPosts() {
		try {
			postRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

}
	

