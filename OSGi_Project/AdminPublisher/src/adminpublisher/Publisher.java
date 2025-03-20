package adminpublisher;

import java.util.ArrayList;

public interface Publisher {
    public void startService();
    public ArrayList<Admin> viewAdmins();
    public int addAdmin(int admin_id, String admin_name, String admin_email, String admin_contactno, String admin_role);
    public int removeAdmin(int admin_id);
    public void updateAdminDetails(int admin_id);
    public void displayMenu();
    // New method for system statistics
    public String getSystemStatistics(String statisticType);
}