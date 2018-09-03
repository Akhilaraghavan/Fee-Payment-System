package com.simcode.fps.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.simcode.fps.repository.model.FeePayment;
import com.simcode.fps.repository.model.FeePayment.PaymentMode;
import com.simcode.fps.web.controller.FeePaymentController;

public class FeePaymentDto {

	private String id;
	private String studentId;
	private String paymentDate;
	private String receiptNumber;
	private String amountPaid;
	private String chequeNumber;
	private String paymentMode;
	private String balance;
	
	public FeePayment create() {
		FeePayment feePayment = new FeePayment();
		if (id != null && !id.isEmpty()) {
			feePayment.setId(Long.valueOf(id));
		}
		
		feePayment.setAmountPaid(new BigDecimal(amountPaid));
		feePayment.setPaymentDate(LocalDate.parse(paymentDate, DateTimeFormatter.ISO_DATE));
		
		if (chequeNumber != null && !chequeNumber.isEmpty())
			feePayment.setChequeNumber(Long.valueOf(chequeNumber));
		
		feePayment.setPaymentMode(PaymentMode.valueOf(paymentMode.toUpperCase()));
		
		if (receiptNumber.isEmpty()) {
			feePayment.setReceiptNumber(FeePaymentController.getCount());
		} else {
			feePayment.setReceiptNumber(Long.valueOf(receiptNumber));
		}
		return feePayment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

}
