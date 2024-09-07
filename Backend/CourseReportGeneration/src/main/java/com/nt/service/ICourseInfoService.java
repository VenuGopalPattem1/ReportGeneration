package com.nt.service;

import java.util.List;
import java.util.Set;

import com.nt.model.PrintData;
import com.nt.model.SearchData;

import jakarta.servlet.http.HttpServletResponse;

public interface ICourseInfoService {

	public Set<String> getCouseCategory();
	public Set<String> getFacultyName();
	public Set<String> getCoureMode();
	
	public List<PrintData> getTotalPrintData();
	
	public List<PrintData> showCourseByFilters(SearchData inputs);
	
	public void getPdfReport(SearchData inputs,HttpServletResponse res) throws Exception;
	public void getExcelReport(SearchData inputs,HttpServletResponse res) throws Exception;
	
	public void getAllDataPdfReport(HttpServletResponse res) throws Exception;
	public void getAllDataExcelReport(HttpServletResponse res) throws Exception;
}
