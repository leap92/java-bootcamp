package com.leap92.bootcamp.RESTMeeting.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leap92.bootcamp.RESTMeeting.domain.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
	
	Collection<Meeting> findById (Long id);

}
