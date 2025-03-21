package studentmanagement_publisher;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement_ServiceImpl implements IStudentManagement_Services {

    private ArrayList<Student> studentlist = new ArrayList<Student>();
    Scanner sc = new Scanner(System.in); 
    @Override
    public void startService() {
        System.out.println("Student Management services Started");
        
        Student Student_1 = new Student(1, "Sanjaya Jayasinghe", "SE", 3);
        Student Student_2 = new Student(2, "Wimukthi Dislshan", "SE", 3);
        Student Student_3 = new Student(3, "Imesah Perera", "SE", 3);
        Student Student_4 = new Student(4, "Rajith Thewarapperuma", "SE", 3);
        
        studentlist.add(Student_1);
        studentlist.add(Student_2);
        studentlist.add(Student_3);
        studentlist.add(Student_4);
    }

    @Override
    public ArrayList<Student> viewStudents() {
        return studentlist;
    }

    @Override
    public int Addstudents(int std_id, String std_name, String std_degreeprogram, int std_year) {
        
        Student newStudent = new Student(std_id, std_name, std_degreeprogram, std_year);
        studentlist.add(newStudent);
        
        return 1;
    }

    @Override
    public int removestudent(int id) {
        int positionArr = id - 1;
        studentlist.remove(positionArr);
        return 1;
    }

    @Override
    public void displayMenu() {
        
    	System.out.println("=======================");
        System.out.println("Student Management MENU");
        System.out.println("=======================");
        System.out.println();
        System.out.println("1. ADD Student");
        System.out.println("2. VIEW Student");
        System.out.println("3. DELETE Student");
        System.out.println("4. UPDATE Student");
        System.out.println("5. Quite");
        System.out.println();
        System.out.println("=======================");
    }

    @Override
    public void updatestudentdetails(int std_id) {
        ArrayList<Student> studentlist = viewStudents();
        for(int i=0;i<studentlist.size();i++) {
            Student student = studentlist.get(i);
            if(std_id == student.getStd_id()) {
                System.out.println("Student Index: "+ student.getStd_id());
                System.out.println("Student Name: "+ student.getStd_name());
                System.out.println("Degree Program: "+ student.getStd_degreeprogram());
                System.out.println("Year: "+ student.getStd_year());
                System.out.println("=======================");
                System.out.print("Are You Sure Want To Update:");
                char delete = sc.next().charAt(0);
                if(delete=='Y' || delete=='y') {
                      System.out.print("Enter the Student Name:");
                      String student_name = sc.next();
                      System.out.print("Enter the Degree Program:");
                      String degree_program = sc.next();
                      sc.nextLine();
                      System.out.print("Enter the year:");
                      int std_year = sc.nextInt();
                      
                      student.setStd_name(student_name);
                      student.setStd_degreeprogram(degree_program);
                      student.setStd_year(std_year);
                      
                      System.out.println("Update successfully completed");
                }
            }
        }
    }
    
    @Override
    public double viewSemesterFees(int year, int semester) {
        double fee = 0;
        
        // Fee calculation based on year and semester
        if (year == 1) {
            fee = 150000.0; // Year 1, any semester
        } else if (year == 2) {
            fee = 250000.0; // Year 2, any semester
        } else if (year == 3) {
            fee = 300000.0; // Year 3, any semester
        } else if (year == 4) {
            if (semester == 1) {
                fee = 350000.0; // Year 4, semester 1
            } else if (semester == 2) {
                fee = 400000.0; // Year 4, semester 2
            }
        }
        
        return fee;
    }
}