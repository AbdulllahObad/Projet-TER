package um.fds.agl.ter22.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import um.fds.agl.ter22.entities.Student;
import um.fds.agl.ter22.entities.TERProject;
import um.fds.agl.ter22.forms.StudentForm;
import um.fds.agl.ter22.forms.TERProjectForm;
import um.fds.agl.ter22.services.TERProjectService;
import um.fds.agl.ter22.services.StudentService;
import um.fds.agl.ter22.services.TERManagerService;
import um.fds.agl.ter22.services.TeacherService;

@Controller
public class TERProjectController implements ErrorController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TERProjectService terProjectService;

    @GetMapping("/listProjects")
    public Iterable<TERProject> getProjects(Model model) {
        Iterable<TERProject> projects = terProjectService.getProjects();
        model.addAttribute("projects", projects);
        return projects;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_TEACHER')")
    @GetMapping(value = { "/addProject" })
    public String showAddProjectPage(Model model) {

        TERProjectForm terProjectForm = new TERProjectForm();
        model.addAttribute("terProjectForm", terProjectForm);

        return "addProject";
    }

    @PostMapping(value = { "/addProject" })
    public String addProject(Model model, @ModelAttribute("TERProjectForm") TERProjectForm terProjectForm) {
        TERProject project;
        if (terProjectService.findById(terProjectForm.getId()).isPresent()) {
            project = terProjectService.findById(terProjectForm.getId()).get();
            project.setTitle(terProjectForm.getTitle());
            project.setTeacher(teacherService.getTeacherByLastName(terProjectForm.getTeacher()));
            project.setTeacher(teacherService.getTeacherByLastName(terProjectForm.getTeacher2()));
            project.setStudent(studentService.getStudentByLastName(terProjectForm.getStudent()));
        } else { //faut mettre else if le nom de student or teacher n'existe pas faut pas construire le project
            project = new TERProject(terProjectForm.getTitle(),
                    teacherService.getTeacherByLastName(terProjectForm.getTeacher()),
                    teacherService.getTeacherByLastName(terProjectForm.getTeacher2()),
                    studentService.getStudentByLastName(terProjectForm.getStudent())
            );
        }
        terProjectService.saveProject(project);
        return "redirect:/listProjects";
    }

    @GetMapping(value = { "/showProjectUpdateForm/{id}" })
    public String showProjectUpdateForm(Model model, @PathVariable(value = "id") long id) {

        TERProjectForm terProjectForm = new TERProjectForm(id,
                terProjectService.findById(id).get().getTitle(),
                terProjectService.findById(id).get().getTeacher().getLastName(),
                terProjectService.findById(id).get().getTeacher2().getLastName(),
                terProjectService.findById(id).get().getStudent().getLastName());
        model.addAttribute("terProjectForm", terProjectForm);
        return "updateProject";
    }

    @GetMapping(value = { "/deleteProject/{id}" })
    public String deleteProject(Model model, @PathVariable(value = "id") long id) {
        terProjectService.deleteProject(id);
        return "redirect:/listProjects";
    }
}
