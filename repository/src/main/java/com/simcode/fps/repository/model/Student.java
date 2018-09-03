package com.simcode.fps.repository.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Table
@Entity(name="STUDENTS")
public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Gender {
		MALE, FEMALE;
	}
	
	public enum Standard {
		NURSERY, LKG, UKG, I, II, III, IV, V, VI, VII; 
	}
	
	@Id
	@Column(name="ID", nullable=false, updatable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="EMAIL")
	private String email;

	@Column(name="FIRSTNAME", nullable=false)
	private String firstName;
	
	@Column(name="MIDDLENAME")
	private String middleName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="GENDER", nullable=false)
	private Gender gender;
	
	@Column(name="DOB", nullable=false)
	private LocalDate dateOfBirth;

	@Column(name="JOINING_DATE", nullable=false)
	private LocalDate joiningDate;
	
	@Column(name="PLACE_OF_BIRTH")
	private String placeOfBirth;
	
	@Column(name="CASTE")
	private String caste;
	
	@Column(name="SUBCASTE")
	private String subCaste;
	
	@Column(name="RELIGION")
	private String religion;
	
	@Column(name="ADHAR_ID", nullable=false)
	private long adharId;
	
	@Column(name="ENROLMENT_ID", nullable=false)
	private long enrollmentId;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FK_CONTACT_ID")
	private Contact contact;

	@OneToMany(mappedBy="student", cascade=CascadeType.ALL)
	private List<FeePayment> feePayments;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="STANDARD", nullable=false)
	private Standard standard;
	
	@OneToOne(mappedBy="student", cascade=CascadeType.ALL)
	private StudentDues studentDues;
	
	@Version
	@Column(name="version", nullable=false)
	private long version;
	
	@Column(name="SECTION")
	private String section;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getSubCaste() {
		return subCaste;
	}

	public void setSubCaste(String subCaste) {
		this.subCaste = subCaste;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public long getAdharId() {
		return adharId;
	}

	public void setAdharId(long adharId) {
		this.adharId = adharId;
	}

	public long getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<FeePayment> getFeePayments() {
		return feePayments;
	}

	public void setFeePayments(List<FeePayment> feePayments) {
		this.feePayments = feePayments;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public StudentDues getStudentDues() {
		return studentDues;
	}

	public void setStudentDues(StudentDues studentDues) {
		this.studentDues = studentDues;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
