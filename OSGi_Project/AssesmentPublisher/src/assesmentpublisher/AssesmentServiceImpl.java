package assesmentpublisher;

import java.util.ArrayList;

public class AssesmentServiceImpl implements IAssesmentServices {

    private ArrayList<Assesment> assesmentsList = new ArrayList<>();

    @Override
    public void startService() {
        System.out.println("Assesment management services Started.");

        Assesment assesment1 = new Assesment("Design and implement a secure network infrastructure",
                "08-04-2025", "29-05-2025", "Network Assessment");
        Assesment assesment2 = new Assesment("Create a backend for an e-commerce application using Node.js and Express",
        		"10-05-2025", "20-06-2025", "Create a Backend");
        Assesment assesment3 = new Assesment("Perform end-to-end testing on a mobile application and generate a test report",
        		"05-06-2025", "25-07-2025", "Test Application");

        assesmentsList.add(assesment1);
        assesmentsList.add(assesment2);
        assesmentsList.add(assesment3);
    }

    @Override
    public void viewAllAssesments() {
        int count = 0;
        for (Assesment assesment : assesmentsList) {
            System.out.println("AssesmentIndex: " + (count + 1));
            System.out.println("Assesment Name: " + assesment.getAssesmentName());
            System.out.println("Assesment Description: " + assesment.getAssesmentDisc());
            System.out.println("Start Date: " + assesment.getStartDate());
            System.out.println("Deadline: " + assesment.getDeadLine());
            System.out.println("---------------------------------");
            count++;
        }
    }

    @Override
    public int addAssesmnets(String assesmentDisc, String startDate, String deadLine, String assesmentName) {
        Assesment newAssesment = new Assesment(assesmentDisc, startDate, deadLine, assesmentName);
        assesmentsList.add(newAssesment);
        return 1;
    }

    @Override
    public int removeAssesment(int index) {
        int arrPosition = index - 1;
        if (arrPosition >= 0 && arrPosition < assesmentsList.size()) {
            assesmentsList.remove(arrPosition);
            return 1;
        } else {
            return 0; // Indicate failure if index is invalid
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("======================================");
        System.out.println("Welcome to assesment management menu");
        System.out.println("======================================\n");
        System.out.println("1. Add Assesments");
        System.out.println("2. View Assesments");
        System.out.println("3. Remove Assesments");
        System.out.println("4. Update Assesments");
        System.out.println("5. Quit");
        System.out.println("\n======================================\n");
    }

    @Override
    public String getAssessmentAnalytics(String analyticsType) {
        switch (analyticsType) {
            case "submissions":
                return "Total Submissions: 87\nOn-time Submissions: 72\nLate Submissions: 15\nMissing Submissions: 13";
            case "grades":
                return "Average Grade: 78.5%\nHighest Grade: 98%\nLowest Grade: 45%\nPass Rate: 92%";
            case "difficulty":
                return "Average Completion Time: 4.2 hours\nDifficulty Rating (1-5): 3.7\nCompletion Rate: 87%";
            case "feedback":
                return "Positive Feedback: 67%\nNegative Feedback: 12%\nNeutral Feedback: 21%\nFeedback Submission Rate: 75%";
            default:
                return "Unknown analytics type requested.";
        }
    }

    // New method to update an assessment
    @Override
    public int updateAssessment(int index, String newAssesmentDisc, String newStartDate, String newDeadLine, String newAssesmentName) {
        int arrPosition = index - 1;
        if (arrPosition >= 0 && arrPosition < assesmentsList.size()) {
            Assesment assesment = assesmentsList.get(arrPosition);
            assesment.setAssesmentDisc(newAssesmentDisc);
            assesment.setStartDate(newStartDate);
            assesment.setDeadLine(newDeadLine);
            assesment.setAssesmentName(newAssesmentName);
            return 1; // Update success
        } else {
            return 0; // Update failure (invalid index)
        }
    }
}
