package lecturepublisher;

public class Lecture {

    private String name;
    private String subject;
    private String subjectCode;
    private String tutor;

    public Lecture(String name, String subject, String subjectCode, String tutor) {
        this.name = name;
        this.subject = subject;
        this.subjectCode = subjectCode;
        this.tutor = tutor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

}
