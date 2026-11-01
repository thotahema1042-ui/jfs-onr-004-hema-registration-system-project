package myjavaminiproject;

import java.util.*;

public class RegistrationManager {

    public static void handleDashboard(Student currstu, ArrayList<Course> predefCatalog, Scanner s) {
        boolean loggedIn = true;
        int o2 = 0;
        
        while (loggedIn) {
            System.out.print("\n----- Student Dashboard -----\n" +"     1. View available courses & labs\n" +"     2. Register a course/lab\n" +"     3. Drop a course/lab\n" +
                             "     4. View your registration\n" +"     5. Logout\n" +"     Choose your option: ");
            
            try 
            {
                o2 = s.nextInt();
                s.nextLine(); 
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Invalid input!! Please enter a number.");
                s.nextLine(); 
            }
            
            switch (o2) {
                case 1:
                    System.out.println("\n----- Available Courses & Labs -----");
                    for (Course c : predefCatalog) {
                        System.out.println("[" + c.getType() + "] ID: " + c.getCid() + 
                                           " | Name: " + c.getCname() + 
                                           " | Credits: " + c.getCcreds() + 
                                           " | Enrolled: " + c.getCenrolled() + "/" + c.getCcap());
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter course ID to register (e.g., S01, L01): ");
                    String regId = s.nextLine();
                    Course targetCourse = null;
                    
                    for (Course c : predefCatalog) {
                        if (c.getCid().equalsIgnoreCase(regId)) {
                            targetCourse = c;
                            break;
                        }
                    }
                    
                    if (targetCourse == null) {
                        System.out.println("Error: Invalid Course ID!");
                        break;
                    }
                    
                    try {
                        // Rule 1: Duplicate check
                        for (Course enrolled : currstu.getEnrolledcourses()) {
                            if (enrolled.getCid().equalsIgnoreCase(regId)) {
                                throw new DuplicateRegisterExcp("You are already registered for this item.");
                            }
                        }
                        
                        // Rule 2: Category limit check (Max 3 SUB, Max 2 LAB)
                        if (targetCourse.getType().equals("SUB") && currstu.getSubCount() >= 3) {
                            throw new InsufficientcredsExcp("You cannot register for more than 3 subjects!");
                        }
                        if (targetCourse.getType().equals("LAB") && currstu.getLabCount() >= 2) {
                            throw new InsufficientcredsExcp("You cannot register for more than 2 labs!");
                        }
                        
                        // Rule 3: Credit boundary check
                        if (currstu.getAvailcreds() < targetCourse.getCcreds()) {
                            throw new InsufficientcredsExcp("Credit limit exceeded! Cannot exceed 13 total credits.");
                        }
                        
                        // Rule 4: Capacity check
                        if (targetCourse.getCenrolled() >= targetCourse.getCcap()) {
                            throw new CourseFullExcp("This course/lab has reached maximum capacity.");
                        }
                        
                        // Apply registration
                        currstu.getEnrolledcourses().add(targetCourse);
                        targetCourse.incrementenrolled();
                        currstu.setAvailcreds(currstu.getAvailcreds() - targetCourse.getCcreds());
                        
                        System.out.println("Successfully registered for " + targetCourse.getCname() + "!");
                        System.out.println("-> Credits left to allocate: " + currstu.getAvailcreds() + "/13");
                        
                    } catch (DuplicateRegisterExcp | InsufficientcredsExcp | CourseFullExcp e) {
                        System.out.println("Registration Failed: " + e.getMessage());
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter course ID to drop out: ");
                    String dropId = s.nextLine();
                    Course dropCourse = null;
                    
                    for (Course c : currstu.getEnrolledcourses()) {
                        if (c.getCid().equalsIgnoreCase(dropId)) {
                            dropCourse = c;
                            break;
                        }
                    }
                    
                    if (dropCourse != null) {
                        boolean droppedSuccessfully = dropCourse.decrementenrolled();
                        if (droppedSuccessfully) {
                            currstu.getEnrolledcourses().remove(dropCourse);
                            currstu.setAvailcreds(currstu.getAvailcreds() + dropCourse.getCcreds());
                            System.out.println("Successfully dropped " + dropCourse.getCname() + ".");
                            System.out.println("-> Credits left to allocate: " + currstu.getAvailcreds() + "/13");
                        } else {
                            System.out.println("Error: Enrollment count is already at zero!");
                        }
                    } else {
                        System.out.println("Error: You are not enrolled in an item with ID " + dropId);
                    }
                    break;
                
                case 4:
                    System.out.println("\n----- Your Registered Courses & Labs -----");
                    if (currstu.getEnrolledcourses().isEmpty()) {
                        System.out.println("No courses or labs selected yet.");
                    } else {
                        for (Course c : currstu.getEnrolledcourses()) {
                            System.out.println("[" + c.getType() + "] " + c.getCid() + " - " + c.getCname() + " (" + c.getCcreds() + " credits)");
                        }
                    }
                    System.out.println("Credits Left to Allocate: " + currstu.getAvailcreds() + "/13");
                    break;
            
                case 5:
                    int subCount = currstu.getSubCount();
                    int labCount = currstu.getLabCount();
                    
                    if (subCount != 3 || labCount != 2 || currstu.getAvailcreds() != 0) {
                        System.out.println("\n[ALERT] Registration incomplete!"+"\nRequirement: Exactly 3 Subjects and 2 Labs (Total 13 credits).");
                        System.out.println("You currently have: " + subCount + " Subjects, " + labCount + " Labs.");
                        System.out.println("Please complete your required selections before logging out.");
                    } else {
                        loggedIn = false;
                        System.out.println("Semester registration locked successfully. Logging out!");
                    }
                    break;
                    
                default:
                    System.out.println("Wrong choice!!...Try again...");
                    break;
            }
        }
    }
}