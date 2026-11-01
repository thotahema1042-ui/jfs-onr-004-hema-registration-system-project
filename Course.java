package myjavaminiproject;

import java.util.*;

public class Course {
	private String cid;
	private String cname;
	private String type;
	private int ccreds;
	
	private int ccap=3;
	private int cenrolled;
	
	
	public Course(String cid, String cname, String type, int ccreds) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.type = type;
		this.ccreds = ccreds;
		this.cenrolled=0;
	}

	public String getCid() {
		return cid;
	}
	
	public String getCname() {
		return cname;
	}
	
	public String getType() {
		return type;
	}
	
	public int getCcreds() {
		return ccreds;
	}
	
	public int getCcap() {
		return ccap;
	}
	
	public int getCenrolled() {
		return cenrolled;
	}
	
	public void incrementenrolled() {
		cenrolled++;
	}
	
	public boolean decrementenrolled() {
		if(cenrolled>0) {
			cenrolled--;
			return true;
		}
		else {
			return false;
		}
	}
	
	public static ArrayList<Course> getPredefinedCatalog(){
		
		ArrayList<Course> catalog=new ArrayList<>();
		
		 catalog.add(new Course("S01", "Java", "SUB", 3));
	        catalog.add(new Course("S02", "Python", "SUB", 3));
	        catalog.add(new Course("S03", "DBMS", "SUB", 3));
	        catalog.add(new Course("S04", "CN", "SUB", 3));
	        catalog.add(new Course("S05", "OS", "SUB", 3));
	        
	        catalog.add(new Course("L01", "Java Lab", "LAB", 2));
	        catalog.add(new Course("L02", "DBMS Lab", "LAB", 2));
	        catalog.add(new Course("L03", "Python Lab", "LAB", 2));
	        
	        return catalog;
	}
	

}
