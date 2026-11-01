package myjavaminiproject;


 class CourseFullExcp extends Exception  {
	 
	 public CourseFullExcp(String m) 
	 {
		 super(m);
	 }	
}
 
 
 class InsufficientcredsExcp extends Exception{
	 
	 public InsufficientcredsExcp (String m) 
	 {
		 super(m);
	 }
 }
 
 class DuplicateRegisterExcp extends Exception{
	 
	 public DuplicateRegisterExcp(String m) 
	 {
		 super(m);
	 }
 }
