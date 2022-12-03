package um.fds.agl.ter22.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Student extends UserTER {

    private @ManyToOne Group group;
    // ici on mettra le groupe

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        String[] roles = { "ROLE_STUDENT" };
        this.setRoles(roles);
    }

    public Student(long id, String firstName, String lastName) {
        super(id, firstName, lastName);
        String[] roles = { "ROLE_STUDENT" };
        this.setRoles(roles);
    }

    public Student() {
    }

}
