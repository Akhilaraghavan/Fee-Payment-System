package com.simcode.fps.web.receipt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.simcode.fps.repository.model.Student;

public class ReceiptGenerator {

	private String amountPaid;
	private String receiptNumber;
	private String paymentDate;
	private Student student;
	private String paymentMode;
	private String chequeNumber;

	public ReceiptGenerator(Student student, String paymentDate, String receiptNumber, String amountPaid, 
			String paymentMode, String chequeNumber) {
		this.student = student;
		this.paymentDate = paymentDate;
		this.receiptNumber = receiptNumber;
		this.amountPaid = amountPaid;
		this.chequeNumber = chequeNumber == null ? "" : chequeNumber;
		this.paymentMode = paymentMode;
	}
	
	public File generate() throws IOException, URISyntaxException {
		InputStream receiptIO = getClass().getResourceAsStream("/receipt.docx");
		return parseAndGenerateReceipt(receiptIO);
	}

	private File parseAndGenerateReceipt(InputStream resource) throws FileNotFoundException, IOException {
		XWPFDocument document = new XWPFDocument(resource);
		Map<String, Boolean> found = new HashMap<>();
		document.getParagraphs().forEach(p -> {
			  p.getRuns().forEach(run -> {
				  String text = run.getText(0);
				  if(text == null || !text.contains("$")) return;
				  
		            if (!found.containsKey("${receiptNumber}")  && text.contains("${receiptNumber}")) {
		                text = text.replace("${receiptNumber}", receiptNumber);
		                run.setText(text, 0);
		                found.put("${receiptNumber}", true);
		                return;
		            } 
		            
		            if (!found.containsKey("${paymentDate}") && text.contains("${paymentDate}")) {
		            	text = text.replace("${paymentDate}", paymentDate);
		                run.setText(text, 0);
		                found.put("${paymentDate}", true);
		                return;
		            }
		            
		            if (!found.containsKey("${studentName}") && text.contains("${studentName}")) {
		            	text = text.replace("${studentName}", student.getFirstName() + " " + student.getLastName());
		                run.setText(text, 0);
		                found.put("${studentName}", true);
		                return;
		            }
		            
		            if (!found.containsKey("${standard}")  && text.contains("${standard}")) {
		            	text = text.replace("${standard}", student.getStandard().name());
		                run.setText(text, 0);
		                found.put("${standard}", true);
		                return;
		            }
		            
		            if (!found.containsKey("${amountPaid}")  && text.contains("${amountPaid}")) {
		            	text = text.replace("${amountPaid}", amountPaid);
		                run.setText(text, 0);
		                found.put("${amountPaid}", true);
		                return;
		            }
		            
		            if (!found.containsKey("${paymentMode}") && text.contains("${paymentMode}")) {
		            	text = text.replace("${paymentMode}", paymentMode);
		                run.setText(text, 0);
		                found.put("${paymentMode}", true);
		                return;
		            }
		            
		            if (!found.containsKey("${chequeNumber}") && text.contains("${chequeNumber}")) {
		            	text = text.replace("${chequeNumber}", "0".equals(chequeNumber) ? "" : chequeNumber);
		                run.setText(text, 0);
		                found.put("${chequeNumber}", true);
		                return;
		            }
		            
		            
		            
			  });
		});
		Path tempPath = Files.createTempFile("mes", ".docx");
		File file = tempPath.toFile();
		document.write(new FileOutputStream(file));
		return file;
	}

	public String getFileName() {
		return new StringJoiner("-").add(student.getFirstName())
				.add(student.getStandard().name())
				.add(receiptNumber)
				.add(".docx")
				.toString();
	}

}
