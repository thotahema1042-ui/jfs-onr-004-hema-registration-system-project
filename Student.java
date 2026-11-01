package myjavaminiproject;

import java.util.*;

public class Student {
	
	private String sid;
	private String sname;
	private int availcreds;
	private ArrayList<Course> enrolledcourses;
	
	
	
	public Student(String sid, String sname) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.availcreds=13;
		this.enrolledcourses=new ArrayList<Course>();
	}

	public String getSid() {
		return sid;
	}
	
	public String getSname() {
		return sname;
	}
	
	public int getAvailcreds() {
		return availcreds;
	}
	public void setAvailcreds(int availcreds) {
		this.availcreds = availcreds;
	}
	public ArrayList<Course> getEnrolledcourses() {
		return enrolledcourses;
	}
	
	public int getSubCount() {
		int c=0;
		for(Course co: enrolledcourses) {
			if(co.getType()!= null && co.getType().equalsIgnoreCase("SUB")) {
				c++;
			}
		}
		return c;
	}
	
	public int getLabCount() {
		int c=0;
		for(Course co:enrolledcourses) {
			if(co.getType()!= null && co.getType().equalsIgnoreCase("LAB")) {
				c++;
			}
		}
		return c;
	}
	

}
