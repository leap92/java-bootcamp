package com.leap92.bootcamp.RESTMeeting.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.leap92.bootcamp.RESTMeeting.domain.Meeting;
import com.leap92.bootcamp.RESTMeeting.repositories.AttendeeRepository;
import com.leap92.bootcamp.RESTMeeting.repositories.MeetingRepository;
import com.leap92.bootcamp.RESTMeeting.repositories.RoomRepository;

@Controller
@RequestMapping("/meetings")
public class MeetingController {
	
	private final MeetingRepository meetingRepository;
	private final AttendeeRepository attendeeRepository;
	private final RoomRepository roomRepository;
	
	@Autowired
	public MeetingController(MeetingRepository meetingRepository,
			AttendeeRepository attendeeRepository, RoomRepository roomRepository) {
		this.meetingRepository = meetingRepository;
		this.attendeeRepository = attendeeRepository;
		this.roomRepository = roomRepository;
	}
	
	@RequestMapping(value = "/{meetingId}", method = RequestMethod.GET)
	Meeting readMeeting (@PathVariable Long meetingId) {
		return this.meetingRepository.findOne(meetingId);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	Collection<Meeting> readMeetings() {
		return this.meetingRepository.findAll();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("meeting", new Meeting());
		return "create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Meeting meeting, Model model) {
		model.addAttribute("meeting", meeting);
		return "meeting";
	}
	
	/*@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting) {
		for (Attendee a : meeting.getAttendees()) {
			validateAttendee(a.getName());
		}
		validateRoom(meeting.getRoom().getName());
		
		this.meetingRepository.save(meeting);
		return new ResponseEntity<Meeting> (meeting, HttpStatus.CREATED);
	}
	
	private void validateRoom(String name) {
		this.roomRepository.findByName(name)
		.orElseThrow(
		() -> new RoomNotFoundException(name));
	}

	private void validateAttendee(String name) {
		this.attendeeRepository.findByName(name)
				.orElseThrow(
				() -> new AttendeeNotFoundException(name));
	}*/
	

}

/*class AttendeeNotFoundException extends RuntimeException {
	
	public AttendeeNotFoundException (String name) {
		super ("could not find attendee" + name + ".");
	}
}

class RoomNotFoundException extends RuntimeException {
	
	public RoomNotFoundException (String name) {
		super ("could not find room" + name + ".");
	}
}*/
