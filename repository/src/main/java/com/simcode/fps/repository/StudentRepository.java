package com.simcode.fps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.simcode.fps.repository.model.Student;
import com.simcode.fps.repository.model.Student.Standard;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findStudentByFirstName(String firstName);
	List<Student> findStudentByLastName(String firstName);
	List<Student> findStudentByStandard(Standard standard);
	List<Student> findStudentByStandardAndSection(Standard standard, String section);

	@Query("select d.student from StudentDues d where d.amountDue >0 order by d.amountDue desc")
	List<Student> findAllStudentsWithDues();
	
}
