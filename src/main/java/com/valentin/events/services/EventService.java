package com.valentin.events.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.valentin.events.models.Comments;
import com.valentin.events.models.Events;
import com.valentin.events.models.Users;
import com.valentin.events.repositories.CommentRepository;
import com.valentin.events.repositories.EventRepository;
import com.valentin.events.repositories.UserRepository;

@Service
public class EventService {
	private final UserRepository userRepository;
	private final EventRepository eventRepository;
	private final CommentRepository commentRepository;
	
	public EventService(UserRepository userRepository, EventRepository eventRepository,
			CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.commentRepository = commentRepository;
    }
	
	public Users registerUser(Users user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Users findUserById(Long id) {
    	Optional<Users> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	}
    	else {
    	    return null;
    	}
    }
    
    public boolean authenticateUser(String email, String password) {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    public List <Users> allUser() {
    	return userRepository.findAll();
    }
    
    public List <Events> allEvents() {
    	return eventRepository.findAll();
    }
    
    public List <Comments> allComments() {
    	return commentRepository.findAll();
    }
    
    public Events findEvent(Long id) {
    	Optional<Events> event = eventRepository.findById(id);
    	
    	if(event.isPresent()) {
            return event.get();
    	}
    	else {
    	    return null;
    	}
    }
    
    public Events createEvent(Events event) {
    	return eventRepository.save(event);
    }
    
    public Comments createComment(Comments comment) {
    	return commentRepository.save(comment);
    }
    
    public void deleteEvent(Events event) {
    	eventRepository.delete(event);
    }
}
