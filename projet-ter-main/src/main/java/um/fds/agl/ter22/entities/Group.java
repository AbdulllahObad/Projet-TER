package um.fds.agl.ter22.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Group {
    private String nom ="No Name";
    private @OneToMany (mappedBy = "group")List<Student> student;
    //   (mappedBy = "group")
    private @Id @GeneratedValue Long id;

    public Group() {
    }

    public Group(String nom) {
        if (!(nom.isBlank())){
            this.nom=nom;
        }
        this.student=new ArrayList<>();

    }

    public String getNom() {
        return nom;
    }


    public Long getId() {
        return id;
    }

    public void setNom(String nom) {
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
