package um.fds.agl.ter22.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Group {
    private String nom ="No Name";
    private @OneToMany (mappedBy = "group")List<Student> student;
    private @Id @GeneratedValue Long id;

    public Group() {
    }

    public Group(String nom) {
        if (!(nom.isBlank())){
            this.nom=nom;
        }
        this.student=new ArrayList<>();

    }
    public Group(String groupName, List<Student> student) {
        if(!(groupName.isBlank()))
            this.nom = nom;

        this.student = student;
    }

    public Group(long id, String nom) {
        this(nom);
        this.id = id;
    }

    public Group(long id, String nom, List<Student> student) {
        this(nom, student);
        this.id = id;
    }


    public String getnom() {
        return nom;
    }

    public Long getId() {
        return id;
    }

    public void setnom(String nom) {
        this.nom = nom;
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
