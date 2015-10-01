package com.leap92.bootcamp.RESTMeeting.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Meeting {
	
	public Meeting() {}
	
	public Meeting(Set<Attendee> attendees, Room room) {
		this.attendees = attendees;
		this.room = room;
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToMany
	private Set<Attendee> attendees = new HashSet<>();
	
	@OneToOne
	private Room room;
	
	private String timeSlot;

	public Long getId() {
		return id;
	}

	public Set<Attendee> getAttendees() {
		return attendees;
	}
	
	public void addAttendee (Attendee attendee) {
		this.attendees.add(attendee);
	}
	
	public void removeAttendee (Attendee attendee) {
		this.attendees.remove(attendee);
	}

	public Room getRoom() {
		return room;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAttendees(Set<Attendee> attendees) {
		this.attendees = attendees;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	
	
	
	

}
