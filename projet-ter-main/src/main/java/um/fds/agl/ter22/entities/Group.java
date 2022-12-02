package um.fds.agl.ter22.entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Group {
    private String nom;
    private @ManyToOne Student student;
    //   private @OneToMany(mappedBy = "group") List<Student> studentList;
    private @Id @GeneratedValue Long id;

    public Group() {
    }

    public Group(String nom, Student student) {
        this.nom = nom;
        this.student = student;
    }

    public String getNom() {
        return nom;
    }

    public Student getStudents() {
        return student;
    }

    public Long getId() {
        return id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setStudents(Student students) {
        student = students;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "nom='" + nom + '\'' +
                ", Students=" + student +
                ", id=" + id +
                '}';
    }


}
