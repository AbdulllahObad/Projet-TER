package um.fds.agl.ter22.forms;

import um.fds.agl.ter22.entities.Student;
import java.util.ArrayList;

public class GroupForm {
    private String nom;

    private Long id;

    public GroupForm(Long id,String nom) {
        this.id=id;
        this.nom = nom;
    }

    public GroupForm() {

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


}
