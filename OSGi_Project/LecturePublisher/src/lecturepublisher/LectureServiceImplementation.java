package lecturepublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LectureServiceImplementation implements ILectureServices {

    private List<Lecture> lectureList = new ArrayList<>();

    @Override
    public void initiateService() {
        System.out.println("Lecture management service is now active.");
        
        // Adding sample lectures
        Lecture sample1 = new Lecture("DS Lecture", "Distributed Systems", "SE3020", "Mr.Eishan");
        Lecture sample2 = new Lecture("ESD Visiting Lecture", "ESD", "IT3010", "Mr.Nelum");
        
        lectureList.add(sample1);
        lectureList.add(sample2);
    }

    @Override
    public List<Lecture> showAllLectures() {
        return lectureList;
    }

    @Override
    public int insertLecture(String name, String subject, String subjectCode, String tutor) {
        Lecture newLecture = new Lecture(name, subject, subjectCode, tutor);
        lectureList.add(newLecture);
        return 1;
    }

    @Override
    public int deleteLecture(int index) {
        int arrayIndex = index - 1; 
        lectureList.remove(arrayIndex);
        return 1;
    }

    @Override
    public int modifyLecture(int index) {
            Scanner scanner = new Scanner(System.in);
			int arrayIndex = index - 1;
			Lecture lecture = lectureList.get(arrayIndex);

			System.out.println("\n=========Lecture Details==============");
			System.out.println("\nName of lecture: " + lecture.getName());
			System.out.println("Subject Name: " + lecture.getSubject());
			System.out.println("Subject Code: " + lecture.getSubject());
			System.out.println("Name of Tutor: " + lecture.getTutor());
			System.out.println("====================================");

			System.out.print("\nDo you want to update the lecture? (Y/N): ");
			char confirmUpdate = scanner.next().charAt(0);

			if (confirmUpdate == 'Y' || confirmUpdate == 'y') {
			    scanner.nextLine();  // Consume newline
			    System.out.print("Enter lecture title: ");
			    String newName = scanner.nextLine();
			    System.out.print("Enter subject name: ");
			    String newSubject = scanner.nextLine();
			    System.out.print("Enter subject code: ");
			    String newSubjectCode = scanner.nextLine();
			    System.out.print("Enter tutor name: ");
			    String newTutor = scanner.nextLine();

			    lecture.setName(newName);
			    lecture.setSubject(newSubject);
			    lecture.setSubjectCode(newSubjectCode);
			    lecture.setTutor(newTutor);

			    System.out.println("\n=========Updated Lecture Details=========");
			    System.out.println("\nName of lecture: " + lecture.getName());
			    System.out.println("Subject Name: " + lecture.getSubject());
			    System.out.println("Subject Code: " + lecture.getSubjectCode());
			    System.out.println("Name of tutor: " + lecture.getTutor());
			}
			
        return 1;
    }

    @Override
    public void showOptionsMenu() {
        System.out.println("\n=======Lecture Management Options:=======");
        System.out.println("\n1. Add Lecture");
        System.out.println("2. Update Lecture");
        System.out.println("3. View All Lectures");
        System.out.println("4. Remove Lecture");
        System.out.println("5. Quit");
        System.out.println();
    }
    
    @Override
    public String getLectureResources(String resourceType) {
        // Hardcoded resources based on the requested type
        switch (resourceType) {
            case "slides":
                return "Available Slides:\n" +
                       "1. Introduction to Distributed Systems.pptx\n" +
                       "2. ESD Architecture Fundamentals.pptx\n" +
                       "3. OSGI Framework Overview.pptx";
            case "videos":
                return "Available Video Lectures:\n" +
                       "1. Distributed Systems - Week 1 (45 mins)\n" +
                       "2. ESD Core Concepts - Week 2 (60 mins)\n" +
                       "3. OSGI Plugin Development - Practical (90 mins)";
            case "notes":
                return "Available Lecture Notes:\n" +
                       "1. Distributed Systems - Chapter 1-3\n" +
                       "2. ESD Supplementary Materials\n" +
                       "3. OSGI Framework - Implementation Guide";
            case "references":
                return "Recommended References:\n" +
                       "1. Distributed Systems: Concepts and Design (5th Edition)\n" +
                       "2. Enterprise Application Architecture Patterns\n" +
                       "3. OSGi in Action: Creating Modular Applications";
            default:
                return "Resource type not found. Available types: slides, videos, notes, references";
        }
    }
}