package students.repository;

import java.util.List;

public interface StudentRepository {

	List<Student> selectAll() throws StudentRepositoryException;

	/**
	 * ein Student per ID laden
	 * 
	 * @param id die ID des Students
	 * @return
	 */
	Student selectById(int id) throws StudentRepositoryException;

	int insertStudent(Student student) throws StudentRepositoryException;

	void updateStudent(Student student) throws StudentRepositoryException;

	void deleteStudent(int id) throws StudentRepositoryException;
	
	void updateStudents (List<Student> students) throws StudentRepositoryException;

}