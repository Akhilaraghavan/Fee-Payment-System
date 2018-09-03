package com.simcode.fps.repository.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT_DUES")
public class StudentDues extends GenericEntity implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "AMOUNT_DUE", nullable = false)
	private BigDecimal amountDue;

	public BigDecimal getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FK_STUDENT_DUES_ID")
	private Student student;

	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}

}
