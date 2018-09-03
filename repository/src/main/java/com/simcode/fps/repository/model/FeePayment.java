package com.simcode.fps.repository.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="FEE_PAYMENTS")
public class FeePayment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum PaymentMode {
		CASH, CHEQUE
	}
	
	@Id
	@Column(name="ID", nullable=false, updatable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="PAYMENT_DATE")
	private LocalDate paymentDate;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="PAYMENT_MODE")
	private PaymentMode paymentMode;

	@Column(name="CHEQUE_NUMBER")
	private long chequeNumber;
	
	@Column(name="RECEIPT_NUMBER")
	private long receiptNumber;

	@Column(name="AMOUNT_PAID")
	private BigDecimal amountPaid;
	
	@OneToOne
	@JoinColumn(name="FK_STUDENT_ID")
	private Student student;
	
	@Version
	private long version;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public long getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(long chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public long getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(long receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
}
