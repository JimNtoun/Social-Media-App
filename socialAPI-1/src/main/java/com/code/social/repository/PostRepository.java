package com.code.social.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.social.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	  List<Post> findByTitleContaining(String title);
	  List<Post> findByPublished(boolean published);
}
