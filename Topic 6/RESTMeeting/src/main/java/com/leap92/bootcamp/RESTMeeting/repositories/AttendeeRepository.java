package com.leap92.bootcamp.RESTMeeting.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.leap92.bootcamp.RESTMeeting.domain.Attendee;

public interface AttendeeRepository extends JpaRepository <Attendee, Long> {
	Optional<Attendee> findByName (String name);
}
