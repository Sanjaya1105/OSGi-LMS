package adminpublisher;

public class Admin {
    private int admin_id;
    private String admin_name;
    private String admin_email;
    private String admin_contactno;
    private String admin_role;

    public Admin(int admin_id, String admin_name, String admin_email, String admin_contactno, String admin_role) {
        super();
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_email = admin_email;
        this.admin_contactno = admin_contactno;
        this.admin_role = admin_role;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public String getAdmin_contactno() {
        return admin_contactno;
    }

    public String getAdmin_role() {
        return admin_role;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public void setAdmin_contactno(String admin_contactno) {
        this.admin_contactno = admin_contactno;
    }

    public void setAdmin_role(String admin_role) {
        this.admin_role = admin_role;
    }
} 