package com.simcode.fps.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import javax.persistence.LockModeType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simcode.fps.exception.StudentNotFoundException;
import com.simcode.fps.repository.model.Contact;
import com.simcode.fps.repository.model.FeePayment;
import com.simcode.fps.repository.model.FeePayment.PaymentMode;
import com.simcode.fps.repository.model.Fees;
import com.simcode.fps.repository.model.Fees.AdmissionType;
import com.simcode.fps.repository.model.Student;
import com.simcode.fps.repository.model.Student.Gender;
import com.simcode.fps.repository.model.Student.Standard;
import com.simcode.fps.service.IStudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ComponentScan(basePackages="com.simcode.fps")
public class StudentDetailsTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IStudentService studentService;
	
	@Test
	public void testStudentDetails() {
		Student student = new Student();
		student.setAdharId(123);
		student.setCaste("Hindu");
		student.setDateOfBirth(LocalDate.of(1999, Month.APRIL, 12));
		student.setEmail("a@gmail.com");
		student.setEnrollmentId(124);
		student.setFirstName("ani");
		student.setGender(Gender.MALE);
		student.setJoiningDate(LocalDate.now());
		student.setLastName("Nar");
		student.setMiddleName("Shuban");
		student.setPlaceOfBirth("Bangalore");
		student.setReligion("Hindu");
		student.setSubCaste("ss");
		student.setStandard(Standard.VI);
		
		entityManager.persist(student);
		
		List<Student> list = studentService.findStudentByFirstName("ani");
		assertEquals(1, list.size());
		assertEquals(student.getFirstName(), list.get(0).getFirstName());
	}
	
	
	@Test
	public void testStudentLock() throws StudentNotFoundException {
		Student student = new Student();
		student.setAdharId(123);
		student.setCaste("Hindu");
		student.setDateOfBirth(LocalDate.of(1999, Month.APRIL, 12));
		student.setEmail("a@gmail.com");
		student.setEnrollmentId(124);
		student.setFirstName("ani");
		student.setGender(Gender.MALE);
		student.setJoiningDate(LocalDate.now());
		student.setLastName("Nar");
		student.setMiddleName("Shuban");
		student.setPlaceOfBirth("Bangalore");
		student.setReligion("Hindu");
		student.setSubCaste("ss");
		student.setStandard(Standard.VI);
		
		final Student s = studentService.save(student);
		s.setAdharId(124555);
		
		Student save = studentService.save(s);
		assertEquals(save.getVersion(), 1);
		
		s.setAdharId(545345);
		studentService.save(s);
		
	}
	
	@Test
	public void testStudentContactDetails() {
		Student student = new Student();
		student.setAdharId(123);
		student.setCaste("Hindu");
		student.setDateOfBirth(LocalDate.of(1999, Month.APRIL, 12));
		student.setEmail("a@gmail.com");
		student.setEnrollmentId(124);
		student.setFirstName("ani");
		student.setGender(Gender.MALE);
		student.setJoiningDate(LocalDate.now());
		student.setLastName("Nar");
		student.setMiddleName("Shuban");
		student.setPlaceOfBirth("Bangalore");
		student.setReligion("Hindu");
		student.setSubCaste("ss");
		student.setStandard(Standard.VI);
		
		Contact contact = new Contact();
		contact.setCity("Bangalore");
		contact.setCountry("India");
		contact.setFatherName("Narain");
		contact.setMotherName("Narain");
		contact.setPermanantAddress("63 wansted lane");
		contact.setPhoneNumber(1234);
		contact.setPostCode(560062);
		contact.setState("Karnataka");
		contact.setStudent(student);
		
		student.setContact(contact);
		
		entityManager.persist(student);

		
		List<Student> list = studentService.findStudentByFirstName("ani");
		assertEquals(1, list.size());
		assertNotNull(list.get(0).getContact());
		assertEquals(student.getContact().getFatherName(), list.get(0).getContact().getFatherName());
	}
	
	@Test
	public void testStudentFeePayments() {
		Student student = new Student();
		student.setAdharId(123);
		student.setCaste("Hindu");
		student.setDateOfBirth(LocalDate.of(1999, Month.APRIL, 12));
		student.setEmail("a@gmail.com");
		student.setEnrollmentId(124);
		student.setFirstName("ani");
		student.setGender(Gender.MALE);
		student.setJoiningDate(LocalDate.now());
		student.setLastName("Nar");
		student.setMiddleName("Shuban");
		student.setPlaceOfBirth("Bangalore");
		student.setReligion("Hindu");
		student.setSubCaste("ss");
		student.setStandard(Standard.VI);
		
		Fees  fees = new Fees();
		fees.setAdmissionType(AdmissionType.NEW_ADMISSION);
		fees.setTotalFees(new BigDecimal("22000.50"));
		fees.setFromDate(LocalDate.now());
		fees.setToDate(LocalDate.now());
		
		entityManager.persist(fees);
		

		FeePayment feePayments = new FeePayment();
		feePayments.setAmountPaid(new BigDecimal("10000"));
		feePayments.setPaymentDate(LocalDate.now());
		feePayments.setPaymentMode(PaymentMode.CASH);
		feePayments.setReceiptNumber(123);
		feePayments.setStudent(student);
		
		student.setFeePayments(Arrays.asList(feePayments));
		
		entityManager.persist(student);

		
		List<Student> list = studentService.findStudentByFirstName("ani");
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).getFeePayments().size());
		assertEquals(feePayments.getAmountPaid(),  list.get(0).getFeePayments().get(0).getAmountPaid());
	}
}
