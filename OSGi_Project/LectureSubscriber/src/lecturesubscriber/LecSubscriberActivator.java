package lecturesubscriber;

import lecturepublisher.ILectureServices;
import lecturepublisher.Lecture;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class LecSubscriberActivator implements BundleActivator {

    private ServiceReference lectureServiceReference;
    private ServiceRegistration lecServiceReg;
    private ILectureServices lectureService;

    public void start(BundleContext context) throws Exception {
    	lecServiceReg = context.registerService(this.getClass().getName(), this, null);
    	lectureServiceReference = context.getServiceReference(ILectureServices.class.getName());
        lectureService = (ILectureServices) context.getService(lectureServiceReference);

        lectureService.initiateService();
        System.out.println("Lecture Management system initialized!!!.");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Lecture Management system shutting down!!!.");
        context.ungetService(lectureServiceReference);
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        String mainLoopChoice = "Y";
        
        try {
            while (mainLoopChoice.equalsIgnoreCase("Y")) {
                // Main Lecture Management submenu
                System.out.println("\n=======Lecture Management System=======");
                System.out.println("\n1. Manage Lectures");
                System.out.println("2. Lecture Resources Center");
                System.out.println("3. Back to Main Menu");
                System.out.println("\n====================================");
                
                System.out.print("Enter your choice: ");
                int mainChoice = scanner.nextInt();
                
                if (mainChoice == 1) {
                    // Original Lecture Management functionality
                    manageLectures(scanner);
                } else if (mainChoice == 2) {
                    // New Lecture Resources Center
                    accessLectureResources(scanner);
                } else if (mainChoice == 3) {
                    System.out.println("Returning to Main Menu");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again!!!.");
                }
                
                if (mainChoice == 3) {
                    break;
                }
                
                System.out.print("\nWould you like to return to the Lecture Management Main Menu? (Y/N): ");
                mainLoopChoice = scanner.next();
                
                // Input validation
                while (!mainLoopChoice.equalsIgnoreCase("Y") && !mainLoopChoice.equalsIgnoreCase("N")) {
                    System.out.println("Invalid input. Please enter Y or N.");
                    System.out.print("Would you like to return to the Lecture Management Main Menu? (Y/N): ");
                    mainLoopChoice = scanner.next();
                }
            }
            
            System.out.println("Thank you for using the Lecture Management System!");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Method to handle original lecture management functionality
    private void manageLectures(Scanner scanner) {
        int choice = 0;
        String continueLoop = "Y";

        while (continueLoop.equalsIgnoreCase("Y")) {
            lectureService.showOptionsMenu();
            System.out.print("Enter your choice: ");
            int userChoice = scanner.nextInt();

            boolean isValidChoice = (userChoice >= 1 && userChoice <= 5);

            while (!isValidChoice) {
                System.out.println("Invalid choice. Please try again!!!.");
                lectureService.showOptionsMenu();
                System.out.print("Enter your choice: ");
                userChoice = scanner.nextInt();
                isValidChoice = (userChoice >= 1 && userChoice <= 5);
            }

            choice = userChoice;

            switch (choice) {
                case 1:
                    addLecture(scanner);
                    break;

                case 2:
                    updateLecture(scanner);
                    break;

                case 3:
                    viewLectures();
                    break;

                case 4:
                    removeLecture(scanner);
                    break;

                case 5:
                    System.out.println("Returning to Lecture Management Main Menu");
                    break;
            }

            if (choice == 5) {
                break;
            }

            System.out.print("\nWould you like to continue managing lectures? (Y/N): ");
            continueLoop = scanner.next();
            
            // Input validation
            while (!continueLoop.equalsIgnoreCase("Y") && !continueLoop.equalsIgnoreCase("N")) {
                System.out.println("Invalid input. Please enter Y or N.");
                System.out.print("Would you like to continue managing lectures? (Y/N): ");
                continueLoop = scanner.next();
            }
        }
    }
    
    // New method for accessing lecture resources
    private void accessLectureResources(Scanner scanner) {
        String resourceLoop = "Y";
        
        while (resourceLoop.equalsIgnoreCase("Y")) {
            System.out.println("\n=======Lecture Resources Center=======");
            System.out.println("\n1. View Lecture Slides");
            System.out.println("2. Access Video Lectures");
            System.out.println("3. Download Lecture Notes");
            System.out.println("4. Browse References");
            System.out.println("5. Return to Lecture Management Menu");
            System.out.println("\n====================================");
            
            System.out.print("Enter your choice: ");
            int resourceChoice = scanner.nextInt();
            
            String resourceType = "";
            String title = "";
            
            switch (resourceChoice) {
                case 1:
                    resourceType = "slides";
                    title = "LECTURE SLIDES";
                    break;
                case 2:
                    resourceType = "videos";
                    title = "VIDEO LECTURES";
                    break;
                case 3:
                    resourceType = "notes";
                    title = "LECTURE NOTES";
                    break;
                case 4:
                    resourceType = "references";
                    title = "REFERENCE MATERIALS";
                    break;
                case 5:
                    System.out.println("Returning to Lecture Management Menu");
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    continue;
            }
            
            if (resourceChoice >= 1 && resourceChoice <= 4) {
                String resources = lectureService.getLectureResources(resourceType);
                
                System.out.println("\n--- " + title + " ---");
                System.out.println(resources);
                
                if (resourceChoice != 4) { // If not browsing references
                    simulateResourceAccess(resourceChoice);
                }
                
                System.out.println("\n====================================");
                
                System.out.print("Would you like to access another resource? (Y/N): ");
                resourceLoop = scanner.next();
                
                // Input validation
                while (!resourceLoop.equalsIgnoreCase("Y") && !resourceLoop.equalsIgnoreCase("N")) {
                    System.out.println("Invalid input. Please enter Y or N.");
                    System.out.print("Would you like to access another resource? (Y/N): ");
                    resourceLoop = scanner.next();
                }
            }
        }
    }
    
    // Simulate resource access with a loading animation
    private void simulateResourceAccess(int resourceType) {
        System.out.print("\nAccessing resource");
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(300);
                System.out.print(".");
            }
            
            switch (resourceType) {
                case 1:
                    System.out.println("\nSlides opened in default viewer.");
                    break;
                case 2:
                    System.out.println("\nVideo lecture started playing.");
                    break;
                case 3:
                    System.out.println("\nLecture notes downloaded successfully.");
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //add lecture
    private void addLecture(Scanner scanner) {
        String name, subject, subjectCode, tutor;

        scanner.nextLine();// Consume newline
        System.out.println("\n========ADD NEW LECTURE=========");
        System.out.println("\nEnter the lecture name: ");
        name = scanner.nextLine();
        System.out.print("Enter the name of subject: ");
        subject = scanner.nextLine();
        System.out.print("Enter the subject code: ");
        subjectCode = scanner.nextLine();
        System.out.print("Enter the tutor name: ");
        tutor = scanner.nextLine();

        int result = lectureService.insertLecture(name, subject, subjectCode, tutor);

        if (result == 1) {
            System.out.println("Lecture added successfully!!!.");
        } else {
            System.out.println("Failed to add lecture. Try again!!!.");
        }
    }

    //update lecture
    private void updateLecture(Scanner scanner) {
        List<Lecture> lectures = lectureService.showAllLectures();
        int count = 0;
        System.out.println("\n==========List of current lectures============");
        for (Lecture lecture : lectures) {
            System.out.println("\nLecture Index: " + (count + 1));
            System.out.println("Name of the lecture: " + lecture.getName());
            System.out.println("Subject name: " + lecture.getSubject());
            System.out.println("Subject code: " + lecture.getSubjectCode());
            System.out.println("Tutor: " + lecture.getTutor());
            count++;
        }

        System.out.print("\nEnter the Lecture Index to update: ");
        int index = scanner.nextInt();
        lectureService.modifyLecture(index);
    }

    private void viewLectures() {
        List<Lecture> lectures = lectureService.showAllLectures();
        System.out.println("\n===========List of current lectures============");
        
        for (Lecture lecture : lectures) {
            System.out.println("Name of the lecture: " + lecture.getName());
            System.out.println("Name of Subject: " + lecture.getSubject());
            System.out.println("Subject code: " + lecture.getSubjectCode());
            System.out.println("Tutor: " + lecture.getTutor());
            System.out.println("==========================");
        }
    }

    //remove lecture
    private void removeLecture(Scanner scanner) {
        List<Lecture> lectures = lectureService.showAllLectures();
        int count = 0;
        System.out.println("\n===========List of current lectures============");

        for (Lecture lecture : lectures) {
            System.out.println("\nLecture Index: " + (count + 1));
            System.out.println("Name of the lecture: " + lecture.getName());
            System.out.println("Name of the Subject: " + lecture.getSubject());
            System.out.println("Subject Code: " + lecture.getSubjectCode());
            System.out.println("Tutor: " + lecture.getTutor());
            System.out.println("=================================");
            count++;
        }

        System.out.print("\nEnter the Lecture Index to remove: ");
        int index = scanner.nextInt();
        lectureService.deleteLecture(index);
    }
}