package ch.makery.address.model;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ch.makery.address.util.CalendarUtil;

public class Event {
	private String name;
	private String type;
	private Time time;
	private Calendar date;
	private Location location;
	private Person[] attendeeList;
	private String dateString;
	private int id;
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	
	
	
	public Event(int id, String name, String type, Location l)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.date = Calendar.getInstance();
		this.dateString = dateFormat.format(this.date.getTime());
		this.location = l;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Time getTime() {
		return time;
	}


	public void setTime(Time time) {
		this.time = time;
	}


	public Calendar getDate() {
		return date;
	}


	public void setDate(Calendar date) {
		this.date = date;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public Person[] getAttendeeList() {
		return attendeeList;
	}


	public void setAttendeeList(Person[] attendeeList) {
		this.attendeeList = attendeeList;
	}


	public String getDateString() {
		return dateString;
	}


	public void setDateString(String dateString) {
		this.dateString = dateString;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public DateFormat getDateFormat() {
		return dateFormat;
	}


	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
}
