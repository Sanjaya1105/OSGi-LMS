package assesmentsubscriber;

import assesmentpublisher.IAssesmentServices;
import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    private ServiceReference assesmentServiceReference;

    public void start(BundleContext context) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Assessment subscriber starting...");

        try {
            assesmentServiceReference = context.getServiceReference(IAssesmentServices.class.getName());
            IAssesmentServices asService = (IAssesmentServices) context.getService(assesmentServiceReference);

            asService.startService();
            mainMenu(sc, asService);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Thank you for using the Assessment service console.");
        context.ungetService(assesmentServiceReference);
    }

    private void mainMenu(Scanner sc, IAssesmentServices asService) {
        String mainLoopChk = "Y";

        while (mainLoopChk.equalsIgnoreCase("Y")) {
            System.out.println("\n======================================");
            System.out.println("Assessment Management Main Menu");
            System.out.println("======================================\n");
            System.out.println("1. Manage Assessments");
            System.out.println("2. Assessment Feedback Center");
            System.out.println("3. Back to Main Menu");
            System.out.println("\n======================================\n");

            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();

            if (mainChoice == 1) {
                manageAssessments(sc, asService);
            } else if (mainChoice == 2) {
                assessmentFeedbackCenter(sc, asService);
            } else if (mainChoice == 3) {
                System.out.println("Returning to Main Menu");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }

            if (mainChoice == 3) {
                break;
            }

            System.out.print("Do you want to return to the Assessment Management Main Menu? (Y/N): ");
            String tempString = sc.next();

            boolean validInput = validateYesNo(sc, tempString);
            if (validInput) {
                mainLoopChk = tempString;
            }
        }

        System.out.println("Thank You!!!");
    }

    private void manageAssessments(Scanner sc, IAssesmentServices asService) {
        int choice;
        String loopChk = "Y";

        while (loopChk.equalsIgnoreCase("Y")) {
            asService.displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

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
                        String tempAssm = sc.next();

                        boolean validInput = validateYesNo(sc, tempAssm);
                        if (validInput) {
                            assmLoop = tempAssm;
                        }
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

            if (choice == 4) {
                break;
            }

            System.out.print("Do you need to return to the menu (Y/N): ");
            String tempString = sc.next();

            boolean validInput = validateYesNo(sc, tempString);
            if (validInput) {
                loopChk = tempString;
            }
        }
    }

    private boolean validateYesNo(Scanner sc, String input) {
        while (!(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N"))) {
            System.out.println("Invalid Input. Please input a valid Letter (Y/N)");
            System.out.print("Do you need to continue (Y/N): ");
            input = sc.next();
        }
        return true;
    }

    private void assessmentFeedbackCenter(Scanner sc, IAssesmentServices asService) {
        String feedbackLoop = "Y";

        while (feedbackLoop.equalsIgnoreCase("Y")) {
            System.out.println("\n======================================");
            System.out.println("Assessment Feedback Center");
            System.out.println("======================================\n");
            System.out.println("1. View Student Feedback");
            System.out.println("2. Assessment Difficulty Ratings");
            System.out.println("3. Submit Instructor Feedback");
            System.out.println("4. Generate Feedback Report");
            System.out.println("5. Return to Assessment Main Menu");
            System.out.println("\n======================================\n");

            System.out.print("Select an option: ");
            int feedbackChoice = sc.nextInt();

            switch (feedbackChoice) {
                case 1:
                    System.out.println("Viewing student feedback...");
                    break;
                case 2:
                    System.out.println("Viewing difficulty ratings...");
                    break;
                case 3:
                    System.out.println("Submitting instructor feedback...");
                    break;
                case 4:
                    System.out.println("Generating feedback report...");
                    break;
                case 5:
                    System.out.println("Returning to Assessment Main Menu");
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    continue;
            }

            System.out.print("Would you like to view another feedback option? (Y/N): ");
            feedbackLoop = sc.next();

            boolean validInput = validateYesNo(sc, feedbackLoop);
            if (!validInput) {
                feedbackLoop = "N";
            }
        }
    }
}
