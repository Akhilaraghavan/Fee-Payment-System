package com.simcode.fps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simcode.fps.repository.model.FeePayment;
import com.simcode.fps.repository.model.Student;

public interface FeePaymentRepository extends JpaRepository<FeePayment, Long>{
	
	List<FeePayment> getFeePaymentsByStudent(Student student); 

}
