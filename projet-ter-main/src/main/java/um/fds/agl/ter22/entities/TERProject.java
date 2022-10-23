package um.fds.agl.ter22.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TERProject {
	private String title;
	private @ManyToOne Teacher teacher;

	private @ManyToOne Teacher teacher2;
	private @ManyToOne Student student;
	private @Id @GeneratedValue Long id;

	public TERProject() {
	}

	public TERProject(String title, Teacher teacher, Student student) {
		this.title = title;
		this.teacher = teacher;
		this.student = student;
	}

	public TERProject(String title, Teacher teacher,Teacher teacher2, Student student) {
		this.title = title;
		this.teacher = teacher;
		this.teacher2 = teacher2;
		this.student = student;
	}




	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return teacher;
	}
	public Teacher getTeacher2() {
		return teacher2;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Project{" +
				"id=" + getId() +
				", Title='" + getTitle() + '\'' +
				", Teacher='" + getTeacher() + '\'' +
				'}';
	}
}
