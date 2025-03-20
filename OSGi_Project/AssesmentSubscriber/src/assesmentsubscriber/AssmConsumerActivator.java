package assesmentsubscriber;

import assesmentpublisher.IAssesmentServices;
import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class AssmConsumerActivator implements BundleActivator {

    private ServiceReference assesmentServiceReference;
    private ServiceRegistration assesmentServiceReg;
    private IAssesmentServices asService;

    Scanner sc = new Scanner(System.in);

    public void start(BundleContext context) throws Exception {
        assesmentServiceReg = context.registerService(this.getClass().getName(), this, null);
        assesmentServiceReference = context.getServiceReference(IAssesmentServices.class.getName());
        asService = (IAssesmentServices) context.getService(assesmentServiceReference);

        asService.startService();
        System.out.println("Assessment management system loaded.");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Assessment management system ended.");
        context.ungetService(assesmentServiceReference);
    }

    public void displayMenu() {
        try {
            String loopChk = "Y";

            while (loopChk.equalsIgnoreCase("Y")) {
                System.out.println("\n======================================");
                System.out.println("Assessment Management Main Menu");
                System.out.println("======================================\n");
                System.out.println("1. Manage Assessments");
                System.out.println("2. Assessment Analytics");
                System.out.println("3. Back to Main Menu");
                System.out.println("\n======================================\n");

                System.out.print("Enter your choice: ");
                int mainChoice = sc.nextInt();
                System.out.println("======================================\n");

                if (mainChoice == 1) {
                    manageAssessments();
                } else if (mainChoice == 2) {
                    viewAssessmentAnalytics();
                } else if (mainChoice == 3) {
                    System.out.println("Returning to Main Menu");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }

                System.out.print("Do you need to return to the Assessment Management Main Menu (Y/N): ");
                loopChk = validateYesNo(sc);
                System.out.println("======================================\n");
            }

            System.out.println("Thank You for using Assessment Management System!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void manageAssessments() {
        int choice;
        String loopChk = "Y";

        while (loopChk.equalsIgnoreCase("Y")) {
            asService.displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println("======================================\n");

            switch (choice) {
                case 1:
                    String assesmentDisc, startDate, deadLine, assesmentName;
                    String assmLoop = "Y";

                    while (assmLoop.equalsIgnoreCase("Y")) {
                        System.out.println("Enter the assessment name: ");
                        assesmentName = sc.nextLine();
                        assesmentName = sc.nextLine();
                        System.out.print("Enter the assessment description: ");
                        assesmentDisc = sc.nextLine();
                        System.out.println("Enter the start date (dd-mm-yyyy): ");
                        startDate = sc.nextLine();
                        System.out.println("Enter the deadline (dd-mm-yyyy): ");
                        deadLine = sc.nextLine();

                        int status = asService.addAssesmnets(assesmentDisc, startDate, deadLine, assesmentName);

                        if (status == 1) {
                            System.out.println("Assessment added successfully.");
                        } else {
                            System.out.println("Cannot add assessment. Try again.");
                        }

                        System.out.print("Do you want to add another assessment (Y/N): ");
                        assmLoop = validateYesNo(sc);
                    }
                    break;

                case 2:
                    asService.viewAllAssesments();
                    break;

                case 3:
                    asService.viewAllAssesments();
                    System.out.println("Enter the Assessment Index to delete: ");
                    int index = sc.nextInt();
                    int delStatus = asService.removeAssesment(index);
                    System.out.println(delStatus == 1 ? "Assessment deleted successfully." : "Cannot delete assessment. Try again.");
                    break;

                case 4:
                    // **Newly Added: Update Assessment Functionality**
                    asService.viewAllAssesments();
                    System.out.print("Enter the Assessment Index to update: ");
                    int updateIndex = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    System.out.print("Enter the new assessment name: ");
                    String newAssesmentName = sc.nextLine();
                    System.out.print("Enter the new assessment description: ");
                    String newAssesmentDisc = sc.nextLine();
                    System.out.print("Enter the new start date (dd-mm-yyyy): ");
                    String newStartDate = sc.nextLine();
                    System.out.print("Enter the new deadline (dd-mm-yyyy): ");
                    String newDeadLine = sc.nextLine();

                    int updateStatus = asService.updateAssessment(updateIndex, newAssesmentDisc, newStartDate, newDeadLine, newAssesmentName);
                    System.out.println(updateStatus == 1 ? "Assessment updated successfully." : "Failed to update assessment. Try again.");
                    break;
            }

            System.out.print("Do you need to return to the assessment menu (Y/N): ");
            loopChk = validateYesNo(sc);
            System.out.println("======================================\n");
        }
    }

    private void viewAssessmentAnalytics() {
        String analyticsLoop = "Y";

        while (analyticsLoop.equalsIgnoreCase("Y")) {
            System.out.println("\n======================================");
            System.out.println("Assessment Analytics Dashboard");
            System.out.println("======================================\n");
            System.out.println("1. Submission Statistics");
            System.out.println("2. Grade Distribution");
            System.out.println("3. Difficulty Analysis");
            System.out.println("4. Student Feedback");
            System.out.println("5. Return to Assessment Main Menu");
            System.out.println("\n======================================\n");

            System.out.print("Select an option: ");
            int analyticsChoice = sc.nextInt();
            System.out.println();

            String analyticsType = "";
            String title = "";

            switch (analyticsChoice) {
                case 1:
                    analyticsType = "submissions";
                    title = "SUBMISSION STATISTICS";
                    break;
                case 2:
                    analyticsType = "grades";
                    title = "GRADE DISTRIBUTION";
                    break;
                case 3:
                    analyticsType = "difficulty";
                    title = "DIFFICULTY ANALYSIS";
                    break;
                case 4:
                    analyticsType = "feedback";
                    title = "STUDENT FEEDBACK";
                    break;
                case 5:
                    System.out.println("Returning to Assessment Management Main Menu");
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    continue;
            }

            String analytics = asService.getAssessmentAnalytics(analyticsType);
            System.out.println("\n--- " + title + " ---");
            System.out.println(analytics);
            System.out.println("\n======================================\n");

            System.out.print("Would you like to view another analytics report? (Y/N): ");
            analyticsLoop = validateYesNo(sc);
            System.out.println("======================================\n");
        }
    }

    private String validateYesNo(Scanner sc) {
        String input;
        while (true) {
            input = sc.next();
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")) {
                return input;
            }
            System.out.println("Invalid Input. Please input a valid Letter (Y/N)");
            System.out.print("Please enter again (Y/N): ");
        }
    }
}
