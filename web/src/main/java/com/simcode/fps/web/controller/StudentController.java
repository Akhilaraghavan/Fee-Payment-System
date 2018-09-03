	package com.simcode.fps.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simcode.fps.exception.StudentNotFoundException;
import com.simcode.fps.repository.model.Student;
import com.simcode.fps.service.IStudentService;
import com.simcode.fps.web.dto.StudentDto;

@Controller
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@PostMapping(path="/api/saveStudent")
	public String saveStudent(StudentDto studentDto, HttpServletRequest httpRequest) {
		studentService.save(studentDto.createEntity());
		return "redirect:/home";
	}
	
	@GetMapping(path="/api/editStudent")
	public String editStudent(@RequestParam(name="id") long id, HttpServletRequest httpRequest, RedirectAttributes redirectAttributes) throws StudentNotFoundException {
		redirectAttributes.addFlashAttribute("student", studentService.findStudentById(id));
		return "redirect:/addstudent";
	}
	
	@GetMapping("/addstudent")
	public String addstudent(Model model, @ModelAttribute("student") Object student) {
		StudentDto studentDto = new StudentDto();
		model.addAttribute(studentDto);
		if (student != null && student instanceof Student) {
			studentDto.copy((Student) student);
		}
		return "addstudent";
	}
	
	@GetMapping("/studentlist")
	public String studentlist(Model model, @ModelAttribute("standard") String standard, 
			@ModelAttribute("section") String section, @ModelAttribute("students") Object students) {
		model.addAttribute("standard", standard);
		model.addAttribute("students", students);
		return "studentlist";
	}
	
	@GetMapping(value="api/liststudents")
	public String listStudents(@RequestParam("standard") String standard, 
			RedirectAttributes redirectAttributes, HttpServletRequest httpRequest) {
		redirectAttributes.addFlashAttribute("standard", standard);
		List<Student> studentsByStandard = studentService.getStudentsByStandard(standard);
		if (studentsByStandard.isEmpty()) {
			studentsByStandard = new ArrayList<>();
		}
		redirectAttributes.addFlashAttribute("students", studentsByStandard);
		return "redirect:/studentlist";
	}
}
