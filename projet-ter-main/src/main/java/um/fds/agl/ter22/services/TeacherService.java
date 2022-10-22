package um.fds.agl.ter22.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.fds.agl.ter22.entities.Student;
import um.fds.agl.ter22.entities.Teacher;
import um.fds.agl.ter22.repositories.TeacherRepository;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Optional<Teacher> getTeacher(final Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher getTeacherByLastName(String lastName) {
        return teacherRepository.findByLastName(lastName);
    }

    public Iterable<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public void deleteTeacher(final Long id) {
        teacherRepository.deleteById(id);
    }

    public Teacher saveTeacher(Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);
        return savedTeacher;
    }

    public Optional<Teacher> findById(long id) {
        return teacherRepository.findById(id);
    }

}