package myjavaminiproject;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Course> predefCatalog = Course.getPredefinedCatalog();
        
        int o1 = 0;
        do {
            System.out.print("\n----- University Course Registration System -----\n" +"     1. Student Login\n" +"     2. Exit\n" +"     Choose your option: ");
            
            try {
                o1 = s.nextInt();
                s.nextLine();
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Invalid input!! Please enter a number (1 or 2).");
                s.nextLine();
            }
            
            switch (o1) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String sid = s.nextLine();
                    System.out.print("Enter Student Name: ");
                    String sname = s.nextLine();
                    
                    Student currstu = new Student(sid, sname);
                    System.out.println("\nWelcome, " + currstu.getSname() + "!");
                    
                    //  dash board operations
                    RegistrationManager.handleDashboard(currstu, predefCatalog, s);
                    break;
                
                case 2:
                    System.out.println("Exiting application. Goodbye!");
                    return;
                   
                default:
                    System.out.println("Invalid choice! Select 1 or 2.");
                    break;
            }
        } while (true);
    }
}