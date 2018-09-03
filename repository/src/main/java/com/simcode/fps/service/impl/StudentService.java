package com.simcode.fps.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simcode.fps.exception.StudentNotFoundException;
import com.simcode.fps.repository.GenericRepository;
import com.simcode.fps.repository.StudentRepository;
import com.simcode.fps.repository.model.Student;
import com.simcode.fps.repository.model.Student.Standard;
import com.simcode.fps.repository.model.StudentDues;
import com.simcode.fps.service.IStudentService;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private GenericRepository<StudentDues> studentDuesRepository;
	
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> getStudentsByStandard(String standard) {
		return studentRepository.findStudentByStandard(Standard.valueOf(standard));
	}

	@Override
	public List<Student> getStudentsByStandardAndSection(String standard, String section) {
		return studentRepository.findStudentByStandardAndSection(Standard.valueOf(standard), section);
	}

	@Override
	public Student save(Student student) {
		return studentRepository.saveAndFlush(student);
	}

	@Override
	public Student findStudentById(long id) throws StudentNotFoundException {
		Optional<Student> optional = studentRepository.findById(id);
		
		if(!optional.isPresent()) {
			throw new StudentNotFoundException("Student Id " + id + " does not exist");
		}
		
		return optional.get();
	}

	public List<Student> findStudentByFirstName(String firstName) {
		return studentRepository.findStudentByFirstName(firstName);
	}

	@Override
	public List<Student> findStudentsWithDues() {
		return studentRepository.findAllStudentsWithDues();
	}

	@Override
	public void saveDues(StudentDues studentDues) {
		studentDuesRepository.save(studentDues);
	}


}
