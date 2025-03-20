package studentmanagement_subscriber;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import studentmanagement_publisher.IStudentManagement_Services;
import studentmanagement_publisher.Student;


public class Subscriber_Activator implements BundleActivator {
    
    private ServiceReference studentmanagement_reference;
    private ServiceRegistration studentManagementReg;
    private IStudentManagement_Services studentservice;
    
    Scanner sc = new Scanner(System.in);
    
    public void start(BundleContext context) throws Exception {
            
        System.out.println("Student management system loaded.");
        studentManagementReg = context.registerService(this.getClass().getName(), this, null);
        studentmanagement_reference = context.getServiceReference(IStudentManagement_Services.class.getName());
        studentservice = (IStudentManagement_Services) context.getService(studentmanagement_reference);
        
        studentservice.startService();

    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Student management system ended.");
        context.ungetService(studentmanagement_reference);
    }
    
    public void displayMenu() {
        
        try {
            
            char menuinput = 'X';
            do {
                // Display the Student Management submenu
            	System.out.println("=======================");
            	System.out.println();
                System.out.println("Student Management");
                System.out.println();
                System.out.println("=======================");
                System.out.println("1. Register Student");
                System.out.println("2. View Semester Payments");
                System.out.println("3. Back to Main Menu");
                System.out.println();
                System.out.println("=======================");
                
                System.out.print("SELECT THE NO: ");
                System.out.println();
                System.out.println();
                int mainChoice = sc.nextInt();
                
                if (mainChoice == 1) {
                    // Original Student Registration submenu
                    char input = 'X';
                    do {
                        studentservice.displayMenu();
                        
                        int choice, indexNo, std_year;
                        String student_name, degree_program;
                        
                        System.out.print("SELECT THE NO:");
                        choice = sc.nextInt();
                        
                        if (choice == 1) {
                            ArrayList<Student> studentlist = studentservice.viewStudents();
                            for (int i = 0; i < studentlist.size(); i++) {
                                System.out.print("Enter the Index No:");
                                indexNo = sc.nextInt();
                                Student student = studentlist.get(i);
                                if (indexNo == student.getStd_id()) {
                                    System.out.println("Index No Already Exists");
                                    break;
                                } else {
                                    System.out.print("Enter the Student Name:");
                                    student_name = sc.next();
                                    System.out.print("Enter the Degree Program:");
                                    degree_program = sc.next();
                                    sc.nextLine();
                                    System.out.print("Enter the year:");
                                    std_year = sc.nextInt();
                                    
                                    studentservice.Addstudents(indexNo, student_name, degree_program, std_year);
                                    System.out.println();
                                    System.out.println("Successfully Completed");
                                    System.out.println("=======================");
                                    break;
                                }
                            }
                            
                            System.out.print("DO YOU WISH TO CONTINUE:");
                            input = sc.next().charAt(0);
                        } else if (choice == 2) {
                            ArrayList<Student> studentlist = studentservice.viewStudents();
                            for (int i = 0; i < studentlist.size(); i++) {
                                Student student = studentlist.get(i);
                                System.out.println("Student Index: " + student.getStd_id());
                                System.out.println("Student Name: " + student.getStd_name());
                                System.out.println("Degree Program: " + student.getStd_degreeprogram());
                                System.out.println("Year: " + student.getStd_year());
                                System.out.println("---------------------------------");
                            }
                            
                            System.out.println();
                            System.out.print("DO YOU WISH TO CONTINUE:");
                            input = sc.next().charAt(0);
                        } else if (choice == 3) {
                            System.out.println("Enter the Student Index to delete: ");
                            int index = sc.nextInt();
                            ArrayList<Student> studentlist = studentservice.viewStudents();
                            for (int i = 0; i < studentlist.size(); i++) {
                                Student student = studentlist.get(i);
                                if (index == student.getStd_id()) {
                                    System.out.println("Student Index: " + student.getStd_id());
                                    System.out.println("Student Name: " + student.getStd_name());
                                    System.out.println("Degree Program: " + student.getStd_degreeprogram());
                                    System.out.println("Year: " + student.getStd_year());
                                    System.out.println("=======================");
                                    System.out.println();
                                    System.out.print("Are You Sure Want To Delete:");
                                    System.out.println();
                                    char delete = sc.next().charAt(0);
                                    if (delete == 'Y' || delete == 'y') {
                                        studentservice.removestudent(index);
                                        System.out.println("Successfully Completed");
                                    }
                                }
                            }
                            
                            System.out.print("DO YOU WISH TO CONTINUE:");
                            input = sc.next().charAt(0);
                        } else if (choice == 4) {
                            System.out.print("Enter the Index No:");
                            indexNo = sc.nextInt();
                            studentservice.updatestudentdetails(indexNo);
                            
                            System.out.print("DO YOU WISH TO CONTINUE:");
                            input = sc.next().charAt(0);
                        } else if (choice == 5) {
                            System.out.println("Returning to Student Management Menu");
                            break;
                        } else {
                            System.out.println("Invalid Selection Please Input");
                            System.out.println();
                            
                            System.out.print("DO YOU WISH TO CONTINUE:");
                            input = sc.next().charAt(0);
                        }
                        
                        System.out.println("=======================");
                        
                        if (choice == 5) {
                            break;
                        }
                        
                    } while (input == 'Y' || input == 'y');
                } else if (mainChoice == 2) {
                    // New functionality: View Semester Payments
                    char feeInput = 'X';
                    do {
                        System.out.println("\n---View Semester Payments---");
                        System.out.print("Enter Year (1-4): ");
                        int year = sc.nextInt();
                        
                        if (year < 1 || year > 4) {
                            System.out.println("Invalid year. Please enter a value between 1 and 4.");
                            continue;
                        }
                        
                        System.out.print("Enter Semester (1-2): ");
                        int semester = sc.nextInt();
                        
                        if (semester < 1 || semester > 2) {
                            System.out.println("Invalid semester. Please enter either 1 or 2.");
                            continue;
                        }
                        
                        double fee = studentservice.viewSemesterFees(year, semester);
                        
                        System.out.println("\nFee Information:");
                        System.out.println("Year: " + year);
                        System.out.println("Semester: " + semester);
                        System.out.println("Fee Amount: " + fee + " LKR");
                        System.out.println("=======================");
                        
                        System.out.print("DO YOU WISH TO CHECK ANOTHER SEMESTER FEE? (Y/N): ");
                        System.out.println();
                        feeInput = sc.next().charAt(0);
                        
                    } while (feeInput == 'Y' || feeInput == 'y');
                } else if (mainChoice == 3) {
                    System.out.println("Returning to Main Menu");
                    break;
                } else {
                    System.out.println("Invalid Selection. Please Try Again.");
                }
                
                if (mainChoice == 3) {
                    break;
                }
                
                System.out.print("DO YOU WISH TO RETURN TO STUDENT MANAGEMENT MENU? (Y/N): ");
                menuinput = sc.next().charAt(0);
                
            } while (menuinput == 'Y' || menuinput == 'y');
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}