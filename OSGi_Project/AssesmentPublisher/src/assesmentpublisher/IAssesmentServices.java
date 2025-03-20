package assesmentpublisher;

public interface IAssesmentServices {
    
    public void startService();
    public void viewAllAssesments();
    public int addAssesmnets(String assesmentDisc, String startDate, String deadLine, String assesmentName);
    public int removeAssesment(int index);
    public void displayMenu();
    public String getAssessmentAnalytics(String analyticsType);
    
    // New method for updating an assessment
    public int updateAssessment(int index, String newAssesmentDisc, String newStartDate, String newDeadLine, String newAssesmentName);
}