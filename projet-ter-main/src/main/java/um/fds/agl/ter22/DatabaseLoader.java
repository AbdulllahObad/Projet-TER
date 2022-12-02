package um.fds.agl.ter22;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import um.fds.agl.ter22.entities.*;
import um.fds.agl.ter22.repositories.*;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final TeacherRepository teachers;
    private final TERManagerRepository managers;

    private final StudentRepository students;
    private final TERProjectRepository terprojects;

    private final GroupRepository groups;


    @Autowired
    public DatabaseLoader(TeacherRepository teachers, TERManagerRepository managers, StudentRepository students, TERProjectRepository terprojects, GroupRepository groups) {
        this.teachers = teachers;
        this.managers=managers;
        this.students=students;
        this.terprojects=terprojects;
        this.groups = groups;
    }

    @Override
    public void run(String... strings) throws Exception {
        TERManager terM1Manager=this.managers.save(new TERManager("Le","Chef", "mdp", "ROLE_MANAGER"));
       SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("Chef", "bigre",
                        AuthorityUtils.createAuthorityList("ROLE_MANAGER"))); // the actual password is not needed here

        this.teachers.save(new Teacher("Ada", "Lovelace", "lovelace",terM1Manager, "ROLE_TEACHER"));
        this.teachers.save(new Teacher("Alan", "Turing", "turing",terM1Manager, "ROLE_TEACHER"));
        this.teachers.save(new Teacher("Leslie", "Lamport", "lamport",terM1Manager, "ROLE_TEACHER"));
        this.students.save(new Student("Gustave", "Flaubert"));
        this.students.save(new Student("Frédéric", "Chopin"));
        this.students.save(new Student("sdf", "sdf"));



        Teacher pro=new Teacher("pro","pro","pro",terM1Manager,"ROLE_TEACHER");
        Student pra=new Student(4444,"pra","pra");
        TERProject proj=new TERProject("Apple",pro,pra);

        Group gr=new Group("gr" );
        //this.groups.save(gr);
        this.students.save(pra);
        this.teachers.save(pro);


       // this.terprojects.save(proj);
        SecurityContextHolder.clearContext();

    }
}
