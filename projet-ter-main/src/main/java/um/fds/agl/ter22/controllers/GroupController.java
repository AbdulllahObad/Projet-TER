package um.fds.agl.ter22.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import um.fds.agl.ter22.repositories.StudentRepository;
import um.fds.agl.ter22.services.GroupService;
import um.fds.agl.ter22.services.StudentService;
import um.fds.agl.ter22.services.TERProjectService;
import um.fds.agl.ter22.services.TeacherService;

@Controller
public class GroupController implements ErrorController {

    public static final String REDIRECT = "redirect:/listGroups";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TERProjectService terProjectService;
    @Autowired
    private GroupService groupService;
    //test the comment
    @GetMapping("/listGroups")
    public Iterable<Group> getGroups(Model model) {
        Iterable<Group> groups = groupService.getGroups();
        model.addAttribute("groups", groups);
        return groups;
    }
    //preAuthorize à ajouter
    @GetMapping(value = { "/createGroup" })
    public String showCreateGroup(Model model) {

        GroupForm groupForm = new GroupForm();
        model.addAttribute("groupForm", groupForm);

        return "createGroup";
    }
    @PostMapping(value = { "/createGroup"})
    public String createGroup(Model model, @ModelAttribute("GroupForm") GroupForm groupForm) {
        Group group=new Group(groupForm.getNom());

        groupService.saveGroup(group);

        return REDIRECT;
    }



}


