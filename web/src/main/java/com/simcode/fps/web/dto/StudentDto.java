package com.simcode.fps.web.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.simcode.fps.repository.model.Contact;
import com.simcode.fps.repository.model.Student;
import com.simcode.fps.repository.model.Student.Gender;
import com.simcode.fps.repository.model.Student.Standard;


public class StudentDto {

	/** 
	 * TODO pattern match
	 */
	private String firstName;
	private String lastName;
	private String middleName;
	private String dateOfBirth;
	private String placeOfBirth;
	private String joiningDate;
	private String adharId;
	private String enrollmentId;
	private String caste;
	private String religion;
	private String subcaste;
	private String email;
	private String motherName;
	private String fatherName;
	private String gender;
	private String standard;
	private String section;
	private String postcode;
	private String permanantAddress;
	private String city;
	private String state;
	private String country;
	private String phoneNumber;
	private String id;
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getPlaceOfBirth() {
		return placeOfBirth;
	}


	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}


	public String getJoiningDate() {
		return joiningDate;
	}


	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}


	public String getAdharId() {
		return adharId;
	}


	public void setAdharId(String adharId) {
		this.adharId = adharId;
	}


	public String getEnrollmentId() {
		return enrollmentId;
	}


	public void setEnrollmentId(String enrollmentId) {
		this.enrollmentId = enrollmentId;
	}


	public String getCaste() {
		return caste;
	}


	public void setCaste(String caste) {
		this.caste = caste;
	}


	public String getReligion() {
		return religion;
	}


	public void setReligion(String religion) {
		this.religion = religion;
	}


	public String getSubcaste() {
		return subcaste;
	}


	public void setSubcaste(String subcaste) {
		this.subcaste = subcaste;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMotherName() {
		return motherName;
	}


	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}


	public String getFatherName() {
		return fatherName;
	}


	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getStandard() {
		return standard;
	}


	public void setStandard(String standard) {
		this.standard = standard;
	}


	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public String getPermanantAddress() {
		return permanantAddress;
	}


	public void setPermanantAddress(String permanantAddress) {
		this.permanantAddress = permanantAddress;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Student createEntity() {
		Student student = new Student();
		if (id != null && !id.isEmpty()) {
			student.setId(getValue(id, Long.class));
		}
		student.setAdharId(getValue(adharId, Long.class));
		student.setEnrollmentId(getValue(enrollmentId, Long.class));
		if (caste != null && !caste.isEmpty()) {
			student.setCaste(caste);
		}
		if (religion != null && !religion.isEmpty()) {
			student.setReligion(religion);
		}
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setMiddleName(middleName);
		student.setDateOfBirth(getValue(dateOfBirth, LocalDate.class));
		student.setPlaceOfBirth(placeOfBirth);
		student.setEmail(email);
		student.setGender(getValue(gender, Gender.class));
		student.setSection(section);
		student.setStandard(getValue(standard, Standard.class));
		student.setJoiningDate(getValue(joiningDate, LocalDate.class));
		student.setSubCaste(subcaste);
		
		Contact contact = new Contact();
		contact.setCity(city);
		contact.setCountry(country);
		contact.setMotherName(motherName);
		contact.setFatherName(fatherName);
		contact.setPermanantAddress(permanantAddress);
		contact.setPostCode(getValue(postcode, Long.class));
		contact.setStudent(student);
		contact.setPhoneNumber(getValue(phoneNumber, Long.class));
		contact.setState(state);

		student.setContact(contact);
		return student;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(String val, Class<T> cls) {
		
		if (Long.class.isAssignableFrom(cls)) {
			return (T) Long.valueOf(val);
		}
		
		if (LocalDate.class.isAssignableFrom(cls)) {
			return (T) LocalDate.parse(val, DateTimeFormatter.ISO_DATE);
		}
		
		if (Gender.class.isAssignableFrom(cls)) {
			return (T) Gender.valueOf(val.toUpperCase());
		}
		
		if(Standard.class.isAssignableFrom(cls)) {
			return (T) Standard.valueOf(val.toUpperCase());
		}
		return (T) val;
		
	}


	public void copy(Student student) {
		setId(String.valueOf(student.getId()));
		setAdharId(String.valueOf(student.getAdharId()));
		setEnrollmentId(String.valueOf(student.getEnrollmentId()));
		
		setFirstName(student.getFirstName());
		setMiddleName(student.getMiddleName());
		setLastName(student.getLastName());
		
		setGender(student.getGender().name());
		setStandard(student.getStandard().name());
		setSection(student.getSection());
		setJoiningDate(student.getJoiningDate().toString());
		setDateOfBirth(student.getDateOfBirth().toString());
		setCaste(student.getCaste());
		setReligion(student.getReligion());
		setSubcaste(student.getSubCaste());
		setPlaceOfBirth(student.getPlaceOfBirth());
		
		Contact contact = student.getContact();
		setMotherName(contact.getMotherName());
		setFatherName(contact.getFatherName());
		setCity(contact.getCity());
		setCountry(contact.getCountry());
		setState(contact.getState());
		setPermanantAddress(contact.getPermanantAddress());
		setPostcode(String.valueOf(contact.getPostCode()));
		setPhoneNumber(String.valueOf(contact.getPhoneNumber()));
		
	}
	
}
