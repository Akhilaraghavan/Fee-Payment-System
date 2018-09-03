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
import javax.persistence.Table;

@Entity
@Table(name="FEES")
public class Fees implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum AdmissionType {
		OLD_ADMISSION, NEW_ADMISSION
	}
	
	@Id
	@Column(name="ID", nullable=false, updatable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="FROM_DATE", nullable=false)
	private LocalDate fromDate;
	
	@Column(name="TO_DATE", nullable=false)
	private LocalDate toDate;
	
	@Column(name="TOTAL_FEES", nullable=false)
	private BigDecimal totalFees;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="ADMISSION_TYPE", nullable=false)
	private AdmissionType admissionType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(BigDecimal totalFees) {
		this.totalFees = totalFees;
	}

	public AdmissionType getAdmissionType() {
		return admissionType;
	}

	public void setAdmissionType(AdmissionType admissionType) {
		this.admissionType = admissionType;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	
}
