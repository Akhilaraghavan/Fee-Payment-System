package com.simcode.fps.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.simcode.fps.repository.model.Fees;
import com.simcode.fps.repository.model.Fees.AdmissionType;

public class FeesDto {

	private String id;
	private String fromDate;
	private String toDate;
	private String totalFees;
	private String admissionType;
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getTotalFees() {
		return totalFees;
	}
	public void setTotalFees(String totalFees) {
		this.totalFees = totalFees;
	}
	public String getAdmissionType() {
		return admissionType;
	}
	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Fees create() {
		Fees fees = new Fees();
		fees.setAdmissionType(AdmissionType.valueOf(admissionType));
		fees.setFromDate(LocalDate.parse(fromDate, DateTimeFormatter.ISO_DATE));
		fees.setToDate(LocalDate.parse(toDate, DateTimeFormatter.ISO_DATE));
		fees.setTotalFees(new BigDecimal(totalFees));
		if (id != null && !id.isEmpty()) {
			fees.setId(Long.valueOf(id));
		}
		return fees;
	}
	
	public void copy(Fees fees) {
		setId(String.valueOf(fees.getId()));
		setAdmissionType(fees.getAdmissionType().name());
		setFromDate(fees.getFromDate().toString());
		setToDate(fees.getToDate().toString());
		setTotalFees(fees.getTotalFees().toString());
	}
	
	
}
