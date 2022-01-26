package students.repository.xml;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import students.repository.Student;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "studentRepository")
public class StudentCollection {

	@XmlElementWrapper(name = "students")
	@XmlElement(name = "student")
	private List<Student> students = new ArrayList<>();

	public List<Student> getStudents() {
		return students;
	}

	private int nextStudentId = 1;

	public int getNextStudentId() {
		return nextStudentId;
	}

	public int incrementNextStudentId() {
		return nextStudentId++;
	}
}
