package com.leap92.bootcamp.RESTMeeting.controllers;


import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leap92.bootcamp.RESTMeeting.domain.Attendee;
import com.leap92.bootcamp.RESTMeeting.repositories.AttendeeRepository;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {
	
	private final AttendeeRepository attendeeRepository;
	
	@Autowired 
	public AttendeeController(AttendeeRepository attendeeRepository) {
		this.attendeeRepository = attendeeRepository;
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	Optional<Attendee> readAttendee (@PathVariable String name) {
		return this.attendeeRepository.findByName(name);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Attendee> getAll() {
		return this.attendeeRepository.findAll();
	}
	
	

}
