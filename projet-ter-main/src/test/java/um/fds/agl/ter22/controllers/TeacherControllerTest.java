package um.fds.agl.ter22.controllers;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import um.fds.agl.ter22.entities.TERProject;
import um.fds.agl.ter22.entities.Teacher;
import um.fds.agl.ter22.services.TeacherService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    TeacherService mockTeacherService;

    TeacherService teacherService ;

    @Test
    @WithMockUser(username = "Chef", roles = "MANAGER")
    void addTeacherGet1() throws Exception{
        mvc.perform(get("/addTeacher"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("addTeacher"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "Chef", roles = "MANAGER") void addTeacherGet2() throws Exception {
        MvcResult result = mvc.perform(get("/addTeacher"))
                              .andExpect(status().isOk())
                              .andExpect(content().contentType("text/html;charset=UTF-8"))
                              .andExpect(view().name("addTeacher"))
                              .andReturn(); }
    @Test
    @WithMockUser(username = "Chef", roles = "MANAGER")
    void addTeacherPostNonExistingTeacher()throws Exception {


        assertTrue(teacherService.getTeacher(10l).isEmpty());

        MvcResult result = mvc.perform(post("/addTeacher")
                        .param("firstName", "Anne-Marie")
                        .param("lastName", "Kermarrec")
                        .param("id", "10")  //envoie de données du formulaire
                )
                .andExpect(status().is3xxRedirection())
                .andReturn();
                 // il faut ici vérifier que le nouvel enseignant a bien été ajouté}
                 assertNotNull(teacherService.getTeacher(10l));



    }

}

