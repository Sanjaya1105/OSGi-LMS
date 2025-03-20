package lecturepublisher;

import java.util.List;

public interface ILectureServices {
    
    public void initiateService();
    public List<Lecture> showAllLectures();
    public int insertLecture(String name, String subject, String subjectCode, String tutor);
    public int deleteLecture(int index);
    public int modifyLecture(int index);
    public void showOptionsMenu();
    // New method for lecture resources
    public String getLectureResources(String resourceType);
}