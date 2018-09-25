package com.simcode.fps.service;

import java.math.BigDecimal;
import java.util.List;

import com.simcode.fps.FeePaymentWrapper;
import com.simcode.fps.exception.FeeStructureNotFoundException;
import com.simcode.fps.exception.StudentNotFoundException;
import com.simcode.fps.repository.model.FeePayment;
import com.simcode.fps.repository.model.Fees;

public interface IFeePaymentService {

	void saveFeeStructure(Fees fees);
	List<Fees> getAllFees();
	Fees findFeesById(long id) throws FeeStructureNotFoundException;
	void deleteFees(long id);
	FeePaymentWrapper getFeePaymentsByStudent(long studentId) throws StudentNotFoundException;
	FeePaymentWrapper saveFeePayment(Long studentId, BigDecimal balance, FeePayment feePayment)
			throws StudentNotFoundException;
}
