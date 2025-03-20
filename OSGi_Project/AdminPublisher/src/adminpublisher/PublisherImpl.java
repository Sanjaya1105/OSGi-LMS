package adminpublisher;

import java.util.ArrayList;
import java.util.Scanner;

public class PublisherImpl implements Publisher {

    private ArrayList<Admin> adminList = new ArrayList<Admin>();
    Scanner sc = new Scanner(System.in); 
    @Override
    public void startService() {
        System.out.println("-------------WELCOME TO THE ADMIN MANAGEMENT SYSTEM-------------");
        
        Admin admin1 = new Admin(1, "Wimu", "wimu@admin.com", "0778956550", "System Admin");
        Admin admin2 = new Admin(2, "San", "san@admin.com", "07177537623", "Content Admin");
        Admin admin3 = new Admin(3, "Imesh", "imesh@admin.com", "0761563877", "User Admin");
        Admin admin4 = new Admin(4, "Rajitha", "rajtha@admin.com", "0725802524", "Security Admin");
        
        adminList.add(admin1);
        adminList.add(admin2);
        adminList.add(admin3);
        adminList.add(admin4);
    }

    @Override
    public ArrayList<Admin> viewAdmins() {
        return adminList;
    }

    @Override
    public int addAdmin(int admin_id, String admin_name, String admin_email, String admin_contactno, String admin_role) {
        Admin newAdmin = new Admin(admin_id, admin_name, admin_email, admin_contactno, admin_role);
        adminList.add(newAdmin);
        return 1;
    }

    @Override
    public int removeAdmin(int id) {
        int positionArr = id - 1;
        adminList.remove(positionArr);
        return 1;
    }

    @Override
    public void displayMenu() {
        System.out.println("\n---Welcome to Admin Part---");
        System.out.println("1. ADD Admin");
        System.out.println("2. VIEW Admins");
        System.out.println("3. DELETE Admin");
        System.out.println("4. UPDATE Admin");
        System.out.println("5. Exit");
        System.out.println();
    }

    @Override
    public void updateAdminDetails(int admin_id) {
        ArrayList<Admin> adminList = viewAdmins();
        for (int i = 0; i < adminList.size(); i++) {
            Admin admin = adminList.get(i);
            if (admin_id == admin.getAdmin_id()) {
                System.out.println("Admin Index: " + admin.getAdmin_id());
                System.out.println("Admin Name: " + admin.getAdmin_name());
                System.out.println("Admin Email: " + admin.getAdmin_email());
                System.out.println("Admin Contact No: " + admin.getAdmin_contactno());
                System.out.println("Admin Role: " + admin.getAdmin_role());
                System.out.println("#######################################");
                System.out.println("=======================================");
                System.out.print("Are You Sure Want To Update (Y/N): ");
                char update = sc.next().charAt(0);
                if (update == 'Y' || update == 'y') {
                    System.out.print("Enter the Admin Name: ");
                    String admin_name = sc.nextLine();
                    admin_name = sc.nextLine();
                    System.out.print("Enter the Admin email: ");
                    String admin_email = sc.nextLine();
                    System.out.print("Enter the contact no: ");
                    String admin_contactno = sc.nextLine();
                    System.out.print("Enter the Admin role: ");
                    String admin_role = sc.nextLine();

                    admin.setAdmin_name(admin_name);
                    admin.setAdmin_email(admin_email);
                    admin.setAdmin_contactno(admin_contactno);
                    admin.setAdmin_role(admin_role);
                }
            }
        }
    }
    
    @Override
    public String getSystemStatistics(String statisticType) {
        // Hardcoded system statistics based on the requested type
        switch (statisticType) {
            case "users":
                return "Active Users: 1250\nInactive Users: 320\nNew Users (Last 30 Days): 156";
            case "performance":
                return "Average Response Time: 0.85 seconds\nPeak Load Time: 1.2 seconds\nSystem Uptime: 99.98%";
            case "storage":
                return "Total Storage: 1024 GB\nUsed Storage: 687 GB\nAvailable Storage: 337 GB";
            case "security":
                return "Failed Login Attempts (Last 24h): 42\nSuccessful Logins (Last 24h): 317\nActive Security Alerts: 2";
            default:
                return "Unknown statistic type requested.";
        }
    }
}