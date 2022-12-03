package um.fds.agl.ter22.controllers;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import um.fds.agl.ter22.entities.TERManager;
import um.fds.agl.ter22.entities.TERProject;
import um.fds.agl.ter22.entities.Teacher;
import um.fds.agl.ter22.services.TeacherService;

import static org.apache.logging.log4j.ThreadContext.isEmpty;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
    @Captor
    ArgumentCaptor<Teacher> captor = ArgumentCaptor.forClass(Teacher.class);


    //@Autowired
    //private TeacherService teacherService ;
/*    ----------- Version TP4 --------------
    @Test
    @WithMockUser(username = "Chef", roles = "MANAGER")
    void addTeacherGet() throws Exception{
        mvc.perform(get("/addTeacher"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("addTeacher"))
                .andReturn();
    }
*/

//       ----------- Version TP4 --------------
    @Test
    @WithMockUser(username = "Chef", roles = "MANAGER")
    void addTeacherGet2() throws Exception {
        MvcResult result = mvc.perform(get("/addTeacher"))
                              .andExpect(status().isOk())
                              .andExpect(content().contentType("text/html;charset=UTF-8"))
                              .andExpect(view().name("addTeacher"))
                              .andReturn(); }
    @Test
    @WithMockUser(username = "Chef", roles = "MANAGER")

    /*       ----------- Version TP4 --------------
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
        assertTrue(teacherService.getTeacher(10l).isPresent());
    }
    */

    void addTeacherPostNonExistingTeacher()throws Exception {
        //Given:
        Teacher teacher = new Teacher(10l, "Anne-Marie", "Kermarrec", new TERManager("Le", "Chef", "mdp", "ROLE_MANAGER"));

        when(mockTeacherService.saveTeacher(any(Teacher.class))).thenReturn(teacher);

        //When:
        mvc.perform(post("/addTeacher")
                        .param("firstName", "Anne-Marie")
                        .param("lastName", "Kermarrec")
                        .param("id", "10")  //envoie de données du formulaire
                )
                .andExpect(status().is3xxRedirection())
                .andReturn();
        //Then:
        verify(mockTeacherService, times(1)).saveTeacher(captor.capture());
        //assertEquals(captor.getValue().getId(), teacher.getId());
        assertEquals(captor.getValue().getFirstName(), teacher.getFirstName());
        assertEquals(captor.getValue().getLastName(), teacher.getLastName());

    }
    /*
        @Test
        @WithMockUser(username = "Chef", roles = "MANAGER")
        void addTeacherPostExistingTeacher()throws Exception{

            mvc.perform(post("/addTeacher")
                            .param("firstName", "Anne")
                            .param("lastName", "Kerma")
                            .param("id", "15")
                    ).andExpect(status().is3xxRedirection())
                    .andReturn();
            assertTrue(teacherService.getTeacher(15l).isPresent());
            mvc.perform(post("/addTeacher")
                            .param("firstName", "John")
                            .param("lastName", "Doe")
                            .param("id", "15")
                    ).andExpect(status().is3xxRedirection())
                    .andReturn();

            assertThat(teacherService.getTeacher(15l).get().getFirstName().equals("John"));
            assertEquals(teacherService.getTeacher(15l).get().getLastName(), "Doe");
        }

     */
    @Test
    @WithMockUser(username = "Chef", roles = "MANAGER")
    void addTeacherPostExistingTeacher()throws Exception{
        Teacher teacher = new Teacher(15l, "Anne-Marie", "Kermarrec", new TERManager("Le", "Chef", "mdp", "ROLE_MANAGER"));
        Teacher teacherUpdate = new Teacher(15l, "John", "Doe", new TERManager("Le", "Chef", "mdp", "ROLE_MANAGER"));

        when(mockTeacherService.saveTeacher(any(Teacher.class))).thenReturn(teacher, teacherUpdate);


        mvc.perform(post("/addTeacher")
                        .param("firstName", "Anne-Marie")
                        .param("lastName", "Kermarrec")
                        .param("id", "15")
                ).andExpect(status().is3xxRedirection())
                .andReturn();

        verify(mockTeacherService, times(1)).saveTeacher(captor.capture()); //make sure that the method saveTeacher is called at least one time with the captore param
        //assertEquals(captor.getValue().getId(), teacher.getId());
        assertEquals(captor.getValue().getFirstName(), teacher.getFirstName());
        assertEquals(captor.getValue().getLastName(), teacher.getLastName());

        mvc.perform(post("/addTeacher")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("id", "15")
                ).andExpect(status().is3xxRedirection())
                .andReturn();

        verify(mockTeacherService, times(2)).saveTeacher(captor.capture());
        //assertEquals(captor.getValue().getId(), teacherUpdate.getId());
        assertEquals(captor.getValue().getFirstName(), teacherUpdate.getFirstName());
        assertEquals(captor.getValue().getLastName(), teacherUpdate.getLastName());

    }

    }



