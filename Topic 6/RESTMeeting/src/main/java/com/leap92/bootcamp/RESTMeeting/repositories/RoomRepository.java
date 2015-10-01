package com.leap92.bootcamp.RESTMeeting.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leap92.bootcamp.RESTMeeting.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	
	Optional<Room> findByName (String name);
}
