package com.leap92.bootcamp.RESTMeeting.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leap92.bootcamp.RESTMeeting.domain.Room;
import com.leap92.bootcamp.RESTMeeting.repositories.RoomRepository;

@RestController
@RequestMapping("/rooms")
public class RoomController {
	
	private final RoomRepository roomRepository;
	
	@Autowired
	public RoomController (RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	Room readRoom (@PathVariable String name) {
		return this.roomRepository.findByName(name).get();
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Room> getAll() {
		return this.roomRepository.findAll();
	}
	

}
