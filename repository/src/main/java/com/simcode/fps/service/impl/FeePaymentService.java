package com.simcode.fps.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simcode.fps.FeePaymentWrapper;
import com.simcode.fps.exception.FeeStructureNotFoundException;
import com.simcode.fps.exception.StudentNotFoundException;
import com.simcode.fps.repository.FeePaymentRepository;
import com.simcode.fps.repository.FeeRepository;
import com.simcode.fps.repository.StudentRepository;
import com.simcode.fps.repository.model.FeePayment;
import com.simcode.fps.repository.model.Fees;
import com.simcode.fps.repository.model.Student;
import com.simcode.fps.repository.model.StudentDues;
import com.simcode.fps.service.IFeePaymentService;

@Service
public class FeePaymentService implements IFeePaymentService {

	@Autowired
	private FeeRepository feeRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private FeePaymentRepository feePaymentRepository;
	
	@Override
	public void saveFeeStructure(Fees fees) {
		feeRepository.save(fees);
	}

	@Override
	public List<Fees> getAllFees() {
		return feeRepository.findAll();
	}

	@Override
	public Fees findFeesById(long id) throws FeeStructureNotFoundException {
		Optional<Fees> optional = feeRepository.findById(id);
		if (!optional.isPresent()) {
			throw new FeeStructureNotFoundException("Fee structure not found");
		}
		return optional.get();
	}

	@Override
	public void deleteFees(long id) {
		feeRepository.deleteById(id);
	}

	@Override
	public FeePaymentWrapper getFeePaymentsByStudent(long studentId) throws StudentNotFoundException {
		Optional<Student> optional = studentRepository.findById(studentId);
		if (!optional.isPresent()) {
			throw new StudentNotFoundException("Student does not exist");
		}
		Student student = optional.get();
		return new FeePaymentWrapper(student, feePaymentRepository.getFeePaymentsByStudent(student));
	}
	
	@Override
	public FeePaymentWrapper saveFeePayment(Long studentId, BigDecimal balance, FeePayment feePayment) throws StudentNotFoundException {
		Optional<Student> optional = studentRepository.findById(studentId);
		if (!optional.isPresent()) {
			throw new StudentNotFoundException("Student does not exist");
		}
		
		Student student = optional.get();
		StudentDues studentDues = student.getStudentDues();
		if (studentDues == null) {
			studentDues = new StudentDues();
			studentDues.setStudent(student);
			student.setStudentDues(studentDues);
		}
		
		// should probably be its own service
		balance = balance.subtract(feePayment.getAmountPaid());
		studentDues.setAmountDue(balance);
		
		feePayment.setStudent(student);
		student.getFeePayments().add(feePayment);
		
		student = studentRepository.save(student);
		return new FeePaymentWrapper(student, new ArrayList<>(student.getFeePayments()));
	}
}
