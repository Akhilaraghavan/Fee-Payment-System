package com.simcode.fps.service;

import java.util.List;

import com.simcode.fps.exception.StudentNotFoundException;
import com.simcode.fps.repository.model.Student;
import com.simcode.fps.repository.model.StudentDues;

public interface IStudentService {

	List<Student> getAllStudents();
	
	List<Student> getStudentsByStandard(String standard);
	
	List<Student> getStudentsByStandardAndSection(String standard, String section);
	
	Student save(Student student);

	Student findStudentById(long id) throws StudentNotFoundException;

	List<Student> findStudentByFirstName(String firstName);

	List<Student> findStudentsWithDues();

	Student saveDues(StudentDues studentDues);
	
}
