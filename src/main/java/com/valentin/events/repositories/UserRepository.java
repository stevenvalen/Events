package com.valentin.events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.valentin.events.models.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
	List <Users> findAll();
	Users findByEmail (String email);
}
