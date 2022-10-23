package um.fds.agl.ter22.forms;

public class TERProjectForm {
    private long id;
    private String title;
    private String teacher;

    // secande Teacher
    private String teacher2;
    private String student;

    public TERProjectForm() {
    }

    public TERProjectForm(long id, String title, String teacher, String student) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.student = student;
    }

    public TERProjectForm(long id, String title, String teacher, String teacher2, String student) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.teacher2 = teacher2;
        this.student = student;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }
    public String getTeacher2() {
        return this.teacher2;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setTeacher2(String teacher2) {
        this.teacher2 = teacher2;
    }

}
