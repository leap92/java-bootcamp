package com.leap92.bootcamp.RESTMeeting;


import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.leap92.bootcamp.RESTMeeting.domain.Attendee;
import com.leap92.bootcamp.RESTMeeting.domain.Room;
import com.leap92.bootcamp.RESTMeeting.repositories.AttendeeRepository;
import com.leap92.bootcamp.RESTMeeting.repositories.RoomRepository;


/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App 
{
	
	@Bean
	CommandLineRunner initAttendees(AttendeeRepository attendeeRepository) {
		return (evt) -> Arrays.asList(
				"Leandro Pross,Baul Saratti,Juan Martin del Valle".split(","))
				.forEach(
						a-> {
							attendeeRepository.save(new Attendee(a));
						});
	}
	
	@Bean
	CommandLineRunner initRooms(RoomRepository roomRepository) {
		return (evt) -> Arrays.asList(
				"MR A,MR B,MR C".split(","))
				.forEach(
						a-> {
							roomRepository.save(new Room(a));
						});
	}
	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
