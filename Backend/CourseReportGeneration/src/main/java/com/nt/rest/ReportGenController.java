package com.nt.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.PrintData;
import com.nt.model.SearchData;
import com.nt.service.CourseInfoServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/app")
@CrossOrigin
public class ReportGenController {
	@Autowired
	private CourseInfoServiceImpl ser;
	
	 @Operation(
		      summary = "Retrieve a Unique Course Data",
		      description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.",
		      tags = { "Unique Courses", "get" })
	@GetMapping("/course")
	public ResponseEntity<?> getCouseData(){
		try {
			Set<String> data=ser.getCouseCategory();
			return new ResponseEntity<Set<String>>(data,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping("/faculty")
	public ResponseEntity<?> getFaculty(){
		try {
			Set<String> data=ser.getFacultyName();
			return new ResponseEntity<Set<String>>(data,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/mode")
	public ResponseEntity<?> getCourseMode(){
		try {
			Set<String> data=ser.getCoureMode();
			return new ResponseEntity<Set<String>>(data,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/filters")
	public ResponseEntity<?> getCouseByFilters(@RequestBody SearchData data){
		try {
			List<PrintData> data1=ser.showCourseByFilters(data);
			return new ResponseEntity<	List<PrintData>>(data1,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@PostMapping("/pdf")
	public void getCouseListInPdf(@RequestBody SearchData data,HttpServletResponse res){
		try {
			//set the response content type
			res.setContentType("application/pdf");
			//set teh content diposition header to response contenet going to browser as downoladable file
			res.setHeader("Content-Disposition", "attachment;fileName=courses.pdf");
			ser.getPdfReport(data, res);		
		}
		catch (Exception e) {
			e.printStackTrace()	;
		}
	}
	
	
	@PostMapping("/xl-all")
	public void getCoursesAllInfoInExl(HttpServletResponse res){
		try {
			//set the response content type
			res.setContentType("application/vnd.ms-excel");
			//set teh content diposition header to response contenet going to browser as downoladable file
			res.setHeader("Content-Disposition", "attachment;fileName=courses.xls");
			ser.getAllDataExcelReport(res);	
		}
		catch (Exception e) {
			e.printStackTrace()	;
		}
	}
	
	
	@PostMapping("/pdf-all")
	public void getCouseListInPdf(HttpServletResponse res){
		try {
			//set the response content type
			res.setContentType("application/pdf");
			//set teh content diposition header to response contenet going to browser as downoladable file
			res.setHeader("Content-Disposition", "attachment;fileName=courses.pdf");
			ser.getAllDataPdfReport(res);		
		}
		catch (Exception e) {
			e.printStackTrace()	;
		}
	}
	
	
	@PostMapping("/xl")
	public void getCoursesAllInfoInExl(@RequestBody SearchData data,HttpServletResponse res){
		try {
			//set the response content type
			res.setContentType("application/vnd.ms-excel");
			//set teh content diposition header to response contenet going to browser as downoladable file
			res.setHeader("Content-Disposition", "attachment;fileName=courses.xls");
			ser.getExcelReport(data, res);	
		}
		catch (Exception e) {
			e.printStackTrace()	;
		}
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getTotalPrintData(){
		try {
			List<PrintData> data1=ser.getTotalPrintData();
			return new ResponseEntity<	List<PrintData>>(data1,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
