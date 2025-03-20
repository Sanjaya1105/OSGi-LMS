package adminsubscriber;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import adminpublisher.Admin;
import adminpublisher.Publisher;
import adminpublisher.PublisherImpl;

public class SubscriberActivator implements BundleActivator {

    private ServiceReference serviceReference;
    private ServiceRegistration adminServiceReg;
    private Publisher adminService;

    Scanner sc = new Scanner(System.in);
    
    public void start(BundleContext context) throws Exception {
        adminServiceReg = context.registerService(this.getClass().getName(), this, null);
        serviceReference = context.getServiceReference(Publisher.class.getName());
        adminService = (Publisher) context.getService(serviceReference);
        
        System.out.println("Administrator system loaded");
        adminService.startService();
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Administrator system ended.");
        context.ungetService(serviceReference);
    }
    
    public void displayMenu() {
        try {
            char menuinput = 'X';
            do {
                // Main Admin sub-menu
                System.out.println("\n---Admin Management---");
                System.out.println("1. Admin Account Management");
                System.out.println("2. System Statistics");
                System.out.println("3. Back to Main Menu");
                System.out.println();
                
                System.out.print("SELECT THE NO: ");
                int mainChoice = sc.nextInt();
                
                if (mainChoice == 1) {
                    // Original Admin Account Management submenu
                    char input = 'X';
                    do {
                        adminService.displayMenu();

                        int choice, indexNo, admin_id;
                        String admin_name, admin_email, admin_contactno, admin_role;

                        System.out.print("SELECT THE NO:");
                        choice = sc.nextInt();
                        
                        if (choice == 1) {
                            ArrayList<Admin> adminList = adminService.viewAdmins();
                            for (int i = 0; i < adminList.size(); i++) {
                                System.out.print("Enter the Index No:");
                                indexNo = sc.nextInt();
                                Admin admin = adminList.get(i);
                                if (indexNo == admin.getAdmin_id()) {
                                    System.out.println("Index No Already Exists");
                                } else {
                                    System.out.print("Enter the Admin Name:");
                                    admin_name = sc.nextLine();
                                    admin_name = sc.nextLine();
                                    System.out.print("Enter the email:");
                                    admin_email = sc.next();
                                    System.out.print("Enter the contact no:");
                                    admin_contactno = sc.next();
                                    System.out.print("Enter the admin role:");
                                    admin_role = sc.next();

                                    adminService.addAdmin(indexNo, admin_name, admin_email, admin_contactno, admin_role);
                                    System.out.println("Successfully Completed");
                                    System.out.println("#######################################");
                                    System.out.println("=======================================");
                                    break;
                                }
                            }
                            
                            System.out.print("DO YOU WISH TO CONTINUE:");
                            input = sc.next().charAt(0);
                        } else if (choice == 2) {
                            ArrayList<Admin> adminList = adminService.viewAdmins();
                            for (int i = 0; i < adminList.size(); i++) {
                                Admin admin = adminList.get(i);
                                System.out.println("Admin Index: " + admin.getAdmin_id());
                                System.out.println("Admin Name: " + admin.getAdmin_name());
                                System.out.println("Admin Email: " + admin.getAdmin_email());
                                System.out.println("Admin Contact No: " + admin.getAdmin_contactno());
                                System.out.println("Admin Role: " + admin.getAdmin_role());
                                System.out.println("#######################################");
                                System.out.println("=======================================");
                            }
                            
                            System.out.print("DO YOU WISH TO CONTINUE:");
                            input = sc.next().charAt(0);
                        } else if (choice == 3) {
                            System.out.println("Enter the Admin Index number to delete: ");
                            int index = sc.nextInt();
                            ArrayList<Admin> adminList = adminService.viewAdmins();
                            for (int i = 0; i < adminList.size(); i++) {
                                Admin admin = adminList.get(i);
                                if (index == admin.getAdmin_id()) {
                                    System.out.println("Admin Index: " + admin.getAdmin_id());
                                    System.out.println("Admin Name: " + admin.getAdmin_name());
                                    System.out.println("Admin Email: " + admin.getAdmin_email());
                                    System.out.println("Admin Contact No: " + admin.getAdmin_contactno());
                                    System.out.println("Admin Role: " + admin.getAdmin_role());
                                    System.out.println("#######################################");
                                    System.out.println("=======================================");
                                    System.out.print("Are You Sure Want To Delete:");
                                    char delete = sc.next().charAt(0);
                                    if (delete == 'Y' || delete == 'y') {
                                        adminService.removeAdmin(index);
                                        System.out.println("Successfully Completed");
                                    }
                                }
                            }
                            
                            System.out.print("DO YOU WISH TO CONTINUE:");
                            input = sc.next().charAt(0);
                        } else if (choice == 4) {
                            System.out.print("Enter the Admin Index number to update:");
                            indexNo = sc.nextInt();
                            adminService.updateAdminDetails(indexNo);
                            
                            System.out.print("DO YOU WISH TO CONTINUE:");
                            input = sc.next().charAt(0);
                        } else if (choice == 5) {
                            System.out.println("Returning to Admin Management Menu");
                            break;
                        } else {
                            System.out.println("Invalid Selection Please Input");
                            
                            System.out.print("DO YOU WISH TO CONTINUE:");
                            input = sc.next().charAt(0);
                        }
                        
                        System.out.println("#######################################");
                        System.out.println("=======================================");
                        
                        if (choice == 5) {
                            break;
                        }
                        
                    } while (input == 'Y' || input == 'y');
                } else if (mainChoice == 2) {
                    // New functionality: System Statistics
                    char statsInput = 'X';
                    do {
                        System.out.println("\n---System Statistics---");
                        System.out.println("1. User Statistics");
                        System.out.println("2. Performance Metrics");
                        System.out.println("3. Storage Information");
                        System.out.println("4. Security Overview");
                        System.out.println("5. Return to Admin Menu");
                        System.out.println();
                        
                        System.out.print("SELECT THE NO: ");
                        int statsChoice = sc.nextInt();
                        
                        String statisticType = "";
                        String title = "";
                        
                        switch (statsChoice) {
                            case 1:
                                statisticType = "users";
                                title = "USER STATISTICS";
                                break;
                            case 2:
                                statisticType = "performance";
                                title = "PERFORMANCE METRICS";
                                break;
                            case 3:
                                statisticType = "storage";
                                title = "STORAGE INFORMATION";
                                break;
                            case 4:
                                statisticType = "security";
                                title = "SECURITY OVERVIEW";
                                break;
                            case 5:
                                System.out.println("Returning to Admin Management Menu");
                                break;
                            default:
                                System.out.println("Invalid selection. Please try again.");
                                continue;
                        }
                        
                        if (statsChoice >= 1 && statsChoice <= 4) {
                            String statistics = adminService.getSystemStatistics(statisticType);
                            
                            System.out.println("\n--- " + title + " ---");
                            System.out.println(statistics);
                            System.out.println("#######################################");
                            System.out.println("=======================================");
                            
                            System.out.print("DO YOU WISH TO CHECK ANOTHER STATISTIC? (Y/N): ");
                            statsInput = sc.next().charAt(0);
                        } else if (statsChoice == 5) {
                            break;
                        }
                        
                    } while (statsInput == 'Y' || statsInput == 'y');
                } else if (mainChoice == 3) {
                    System.out.println("Returning to Main Menu");
                    break;
                } else {
                    System.out.println("Invalid Selection. Please Try Again.");
                }
                
                if (mainChoice == 3) {
                    break;
                }
                
                System.out.print("DO YOU WISH TO RETURN TO ADMIN MANAGEMENT MENU? (Y/N): ");
                menuinput = sc.next().charAt(0);
                
            } while (menuinput == 'Y' || menuinput == 'y');
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}