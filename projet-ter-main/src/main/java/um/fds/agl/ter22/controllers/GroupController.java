package um.fds.agl.ter22.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import um.fds.agl.ter22.entities.Group;
import um.fds.agl.ter22.entities.Student;
import um.fds.agl.ter22.entities.TERProject;
import um.fds.agl.ter22.entities.Teacher;
import um.fds.agl.ter22.forms.GroupForm;
import um.fds.agl.ter22.forms.StudentForm;
import um.fds.agl.ter22.forms.TeacherForm;
import um.fds.agl.ter22.services.GroupService;
import um.fds.agl.ter22.services.StudentService;
import um.fds.agl.ter22.services.TERProjectService;
import um.fds.agl.ter22.services.TeacherService;

@Controller
public class GroupController implements ErrorController {


    @Autowired
    private TERProjectService terProjectService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/listGroups")
    public Iterable<Group> getGroups(Model model) {
        Iterable<Group> groups = groupService.getGroups();
        model.addAttribute("groups", groups);
        return groups;
    }


    }


