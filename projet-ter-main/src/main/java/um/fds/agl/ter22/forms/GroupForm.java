package um.fds.agl.ter22.forms;

import um.fds.agl.ter22.entities.Student;
import java.util.ArrayList;

public class GroupForm {
    private String nom;
    private ArrayList<Student> Students;
    private Long id;

    public GroupForm(Long id,String nom, ArrayList<Student> students) {
        this.id=id;
        this.nom = nom;
        Students = students;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Student> getStudents() {
        return Students;
    }

    public Long getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setStudents(ArrayList<Student> students) {
        Students = students;
    }

}
