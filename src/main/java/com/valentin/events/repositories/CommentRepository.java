package com.valentin.events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.valentin.events.models.Comments;

@Repository
public interface CommentRepository extends CrudRepository <Comments, Long> {
	List <Comments> findAll();
}
