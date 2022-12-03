package um.fds.agl.ter22.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import um.fds.agl.ter22.entities.TERManager;
import um.fds.agl.ter22.entities.Teacher;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest// afin que l'environnement Spring se lance lors du test.

class TeacherRepositoryTest {


    @Autowired
    private TeacherRepository teachers;
    @Autowired
    private TERManagerRepository managers;//  pouvoir accéder au repository des teachers et des managers.



    @Test
    void saveIsPossibleForManager() {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("lechef", "peu importe", AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

        TERManager terM1Manager = new TERManager("Mathieu", "lechef", "mdp", "ROLE_MANAGER");

        this.managers.save(terM1Manager);
        this.teachers.save(new Teacher("Margaret", "Hamilton", "margaret", terM1Manager, "ROLE_TEACHER"));

        assertNotNull(teachers.findByLastName("Hamilton"));

    }

    //  si un enseignant est connecté, lors de l'ajout d'un enseignant, une exception
    // est levée, et l'ajout ne fonctionne pas.

    @Test
    void saveIsNotPossibleForTeacher() {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("leprof", "peu importe", AuthorityUtils.createAuthorityList("ROLE_TEACHER")));

        TERManager terM1Manager = new TERManager("Mathieu", "lechef", "mdp", "ROLE_MANAGER");

        assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> this.teachers.save(new Teacher("Margaret", "Hamilton", "margaret", terM1Manager, "ROLE_TEACHER")));
    }




}