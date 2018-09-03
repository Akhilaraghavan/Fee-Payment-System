package com.simcode.fps.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simcode.fps.exception.FeeStructureNotFoundException;
import com.simcode.fps.repository.model.Fees;
import com.simcode.fps.service.IFeePaymentService;
import com.simcode.fps.web.dto.FeesDto;

@Controller
public class FeeController {

	@Autowired
	private IFeePaymentService feePaymentService;
	
	@PostMapping("/api/saveFeeStructure")
	public String saveFee(FeesDto feesDto, HttpServletRequest httpRequest) {
		Fees fees = feesDto.create();
		feePaymentService.saveFeeStructure(fees);
		return "redirect:/feestructure";
		
	}
	
	@GetMapping("/api/deleteFeeStructure")
	public String deleteFeeStructure(@RequestParam("id") String id, HttpServletRequest httpRequest) {
		feePaymentService.deleteFees(Long.valueOf(id));
		return "redirect:/feestructure";
		
	}
	
	@GetMapping("/feestructure")
	public String feeStructure(Model model) {
		model.addAttribute("fees", feePaymentService.getAllFees());
		return "feestructure";
	}
	
	
	@GetMapping("/api/getFees")
	public @ResponseBody List<Fees> getFees() {
		return feePaymentService.getAllFees();
	}
	
	
	@GetMapping(path="/api/editFeeStructure")
	public @ResponseBody FeesDto feeStructure(@RequestParam(name="id", required=false) String id) throws FeeStructureNotFoundException {
		FeesDto feesDto = new FeesDto();
		if (id != null) {
			Fees fees = feePaymentService.findFeesById(Long.valueOf(id));
			feesDto.copy(fees);
		}
		return feesDto;
	}
	
	
}
