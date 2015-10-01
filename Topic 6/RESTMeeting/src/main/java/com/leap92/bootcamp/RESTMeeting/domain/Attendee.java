package com.leap92.bootcamp.RESTMeeting.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Attendee {
	@Id 
	@GeneratedValue
	private long id;
	
	private String name;
	
	public Attendee() {}
	
	public Attendee (String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}	
	
	
	public void setName(String name) {
		this.name = name;
	}

}
