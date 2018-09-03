package com.simcode.fps.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simcode.fps.exception.StudentNotFoundException;
import com.simcode.fps.repository.model.FeePayment;
import com.simcode.fps.repository.model.Student;
import com.simcode.fps.repository.model.StudentDues;
import com.simcode.fps.service.impl.FeePaymentService;
import com.simcode.fps.service.impl.FeePaymentService.FeePaymentWrapper;
import com.simcode.fps.service.impl.StudentService;
import com.simcode.fps.web.dto.FeePaymentDto;
import com.simcode.fps.web.receipt.ReceiptGenerator;

@Controller
public class FeePaymentController {

	private static final AtomicLong counter = new AtomicLong(1);
	
	@Autowired
	private FeePaymentService feePaymentService;
	
	@Autowired
	private StudentService studentService;
	
	
	@GetMapping("/api/studentPayment")
	public String getStudentPaymentDetails(HttpServletRequest httpRequest, RedirectAttributes redirectAttributes, @RequestParam("id") String studentId) throws StudentNotFoundException {
		redirectAttributes.addFlashAttribute("feePayments", feePaymentService.getFeePaymentsByStudent(Long.valueOf(studentId)));
		return "redirect:/feepayment";
	}
	
	@GetMapping("/feepayment")
	public String getFeePayments(@ModelAttribute("feePayments") Object feePaymentWrapper, Model model) {
		if (feePaymentWrapper instanceof FeePaymentWrapper) {
			model.addAttribute("student", ((FeePaymentWrapper) feePaymentWrapper).getStudent());
			model.addAttribute("studentDues", ((FeePaymentWrapper) feePaymentWrapper).getStudent().getStudentDues());
			model.addAttribute("feePayments", ((FeePaymentWrapper) feePaymentWrapper).getFeePayments());
		}
		return "feepayment";
	}
	
	@PostMapping("/api/saveFeePayment")
	public String savePayment(RedirectAttributes redirectAttributes, HttpServletRequest httpRequest, 
			FeePaymentDto feePaymentDto, Model model) throws StudentNotFoundException {
		FeePayment feePayment = feePaymentDto.create();
		redirectAttributes.addFlashAttribute("feePayments", feePaymentService.saveFeePayment(Long.valueOf(feePaymentDto.getStudentId()), new BigDecimal(feePaymentDto.getBalance()), feePayment));
		return "redirect:/feepayment";
	}
	
	
	@GetMapping(path="api/generateReceipt", produces="application/docx")
	@ResponseBody
	public FileSystemResource generateReceipt(@RequestParam("studentId") long studentId, @RequestParam("paymentDate") String paymentDate,
			@RequestParam("receiptNumber") String receiptNumber, @RequestParam("amountPaid") String amountPaid, 
			 @RequestParam("paymentMode") String paymentMode,  @RequestParam("chequeNumber") String chequeNumber, HttpServletResponse response)
					throws StudentNotFoundException, IOException, URISyntaxException {
		Student student = studentService.findStudentById(studentId);
		ReceiptGenerator receiptGenerator = new ReceiptGenerator(student, paymentDate, receiptNumber, amountPaid, paymentMode, chequeNumber);
		response.setContentType("application/docx");      
		response.setHeader("Content-Disposition", "attachment; filename=\"" + receiptGenerator.getFileName() +"\""); 
		
		return new FileSystemResource(receiptGenerator.generate());
	}
	
	public static long getCount() {
		return counter.getAndIncrement();
	}
	
	@PostMapping(path="/api/saveAmountDue", consumes = MediaType.ALL_VALUE)
	public void saveAmountDue(StudentDue studentDue) throws StudentNotFoundException, JSONException {
		long studentId = Long.valueOf(studentDue.getStudentId());
		
		Student student = studentService.findStudentById(studentId);
		StudentDues studentDues = student.getStudentDues();
		if (studentDues == null) {
			studentDues = new StudentDues();
			studentDues.setStudent(student);
		}
		studentDues.setAmountDue(new BigDecimal(studentDue.getAmountDue()));
		
		studentService.saveDues(studentDues);
	}
	
	public class StudentDue {
		private String studentId;
		private String amountDue;
		
		public String getStudentId() {
			return studentId;
		}
		public void setStudentId(String studentId) {
			this.studentId = studentId;
		}
		public String getAmountDue() {
			return amountDue;
		}
		public void setAmountDue(String amountDue) {
			this.amountDue = amountDue;
		}
		
	}
}
