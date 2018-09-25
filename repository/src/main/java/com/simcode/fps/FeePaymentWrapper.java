package com.simcode.fps;

import java.io.Serializable;
import java.util.List;

import com.simcode.fps.repository.model.FeePayment;
import com.simcode.fps.repository.model.Student;

public class FeePaymentWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Student student;
	private List<FeePayment> feePayments;

	public FeePaymentWrapper(Student student, List<FeePayment> feePayments) {
		super();
		this.student = student;
		this.feePayments = feePayments;
	}

	public Student getStudent() {
		return student;
	}

	public List<FeePayment> getFeePayments() {
		return feePayments;
	}

}
