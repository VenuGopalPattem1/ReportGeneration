package com.nt.service;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.entity.CourseInfo;
import com.nt.model.PrintData;
import com.nt.model.SearchData;
import com.nt.repo.ICourseInfoRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CourseInfoServiceImpl implements ICourseInfoService {
	@Autowired
	private ICourseInfoRepo repo;

	@Override
	public Set<String> getCouseCategory() {
		return repo.getCategoryData();
	}

	@Override
	public Set<String> getFacultyName() {
		// TODO Auto-generated method stub
		return repo.getFacultyData();
	}

	@Override
	public Set<String> getCoureMode() {
		// TODO Auto-generated method stub
		return repo.getCourseModeData();
	}

	@Override
	public void getPdfReport(SearchData inputs, HttpServletResponse res) throws Exception {
		//get the search results
				List<PrintData> lis = showCourseByFilters(inputs);
				 //create document obj(OpenPdf)
				Document doc = new Document(PageSize.A4);
				//get pdf writer to write to the document and response object
				PdfWriter.getInstance(doc, res.getOutputStream());
				//open the document
				doc.open();
				//define font of the paragraph
				Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
				font.setSize(30);
				font.setColor(Color.RED);
				
				//create the para having content and above font style
				Paragraph para = new Paragraph(" Search Report of Course",font);
				para.setAlignment(Paragraph.ALIGN_CENTER);
				//add para to the document
				doc.add(para);
				
				//dispaly the search results as pdf table
				PdfPTable tab= new PdfPTable(10);
				tab.setWidthPercentage(70);
				tab.setWidths(new float[] {5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f});
				tab.setSpacingBefore(2.0f);
				
				//prepare heading row cells int he pdf table
				PdfPCell cell= new PdfPCell();
				cell.setBackgroundColor(Color.GRAY);
				cell.setPadding(5);
				Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				cellFont.setColor(Color.BLACK);
				
				
				cell.setPhrase(new Phrase("Id",cellFont));
				tab.addCell(cell);
				cell.setPhrase(new Phrase("courseName",cellFont));
				tab.addCell(cell);
				cell.setPhrase(new Phrase("location",cellFont));
				tab.addCell(cell);
				cell.setPhrase(new Phrase("courseCategory",cellFont));
				tab.addCell(cell);
				cell.setPhrase(new Phrase("facultyName",cellFont));
				tab.addCell(cell);
				cell.setPhrase(new Phrase("adminName",cellFont));
				tab.addCell(cell);
				cell.setPhrase(new Phrase("adminContact",cellFont));
				tab.addCell(cell);
				cell.setPhrase(new Phrase("couseStatus",cellFont));
				tab.addCell(cell);
				cell.setPhrase(new Phrase("courseMode",cellFont));
				tab.addCell(cell);
				cell.setPhrase(new Phrase("startDate",cellFont));
				tab.addCell(cell);
				
				
				//add data cells to pdf cells
				lis.forEach(info->{
					tab.addCell(String.valueOf(info.getId()));
					tab.addCell(info.getCourseName());
					tab.addCell(info.getLocation());
					tab.addCell(info.getCourseCategory());
					tab.addCell(info.getFacultyName());
					tab.addCell(info.getAdminName());
					tab.addCell(String.valueOf(info.getAdminContact()));
					tab.addCell(info.getCourseStatus());
					tab.addCell(info.getCourseMode());
					tab.addCell(String.valueOf(info.getStartDate()));
				});
				
				//add table to document
				doc.add(tab);
				doc.close();
	}

	@Override
	public void getExcelReport(SearchData inputs, HttpServletResponse res) throws Exception {
		//get the search results
		List<PrintData> lis = showCourseByFilters(inputs);
		
		//create excel workbook
		HSSFWorkbook book = new HSSFWorkbook();
		
		//create sheet in work book
		HSSFSheet sheet1 = book.createSheet("Course details");
		
		//create heading row in sheet1
		HSSFRow headerRow = sheet1.createRow(0);
		headerRow.createCell(0).setCellValue("Id");
		headerRow.createCell(1).setCellValue("courseName");
		headerRow.createCell(2).setCellValue("location");
		headerRow.createCell(3).setCellValue("courseCategory");
		headerRow.createCell(4).setCellValue("facultyName");
		headerRow.createCell(5).setCellValue("adminName");
		headerRow.createCell(6).setCellValue("adminContact");
		headerRow.createCell(7).setCellValue("couseStatus");
		headerRow.createCell(8).setCellValue("courseMode");
		headerRow.createCell(9).setCellValue("startDate");
		
		//add data rows to each sheet
		int i=1;
		for(PrintData data:lis) {
			HSSFRow dataRow = sheet1.createRow(i);
					dataRow.createCell(0).setCellValue(data.getId());
					dataRow.createCell(1).setCellValue(data.getCourseName());
					dataRow.createCell(2).setCellValue(data.getLocation());
					dataRow.createCell(3).setCellValue(data.getCourseCategory());
					dataRow.createCell(4).setCellValue(data.getFacultyName());
					dataRow.createCell(5).setCellValue(data.getAdminName());
					dataRow.createCell(6).setCellValue(data.getAdminContact());
					dataRow.createCell(7).setCellValue(data.getCourseStatus());
					dataRow.createCell(8).setCellValue(data.getCourseMode());
					dataRow.createCell(9).setCellValue(data.getStartDate());
					i++;
		}
		
		//getOutputStream pointing to response obj
		ServletOutputStream ser = res.getOutputStream();
		//write the excel work book data reponse object using above stream
		book.write(ser);
		ser.close();
		book.close();
	}
	
	@Override
	public List<PrintData> showCourseByFilters(SearchData inputs) {
		
		//create a new Search Object
		CourseInfo search = new CourseInfo();
		//check whether the paramenterized seach obj value is valid or not
		//checking each property in seach obj value is valid or not 
		String category = inputs.getCourseCategory();
		if(StringUtils.hasLength(category));
		search.setCourseCategory(category);
		
		String faculty = inputs.getFaculty();
		if(StringUtils.hasLength(faculty));
		search.setFacultyName(faculty);
		
		String mode=inputs.getCourseMode();
		if(StringUtils.hasLength(mode));
		search.setCourseMode(mode);
		
		LocalDateTime startDate = inputs.getStartDate();
		if(!ObjectUtils.isEmpty(startDate))
			search.setStartDate(startDate);
		
		//create a example object and pass the example object to the findAll method param
		Example<CourseInfo> ex=Example.of(search);
		
		//getting all the couse details from the db table
		List<CourseInfo> data=repo.findAll(ex);
		//converting the db table data into the necessary data required for the report generation
		List<PrintData> printData=new ArrayList<PrintData>();
		data.forEach(items->{
			PrintData print =new PrintData();
			BeanUtils.copyProperties(items, print);
			printData.add(print);
		});
		return printData;
	}

	@Override
	public void getAllDataPdfReport(HttpServletResponse res) throws Exception {
		//get the search results
		List<CourseInfo> info1=repo.findAll();
		List<PrintData> lis = new ArrayList<PrintData>();
		info1.forEach(data->{
			PrintData p= new PrintData();
			BeanUtils.copyProperties(data, p);
			lis.add(p);
		});
		 //create document obj(OpenPdf)
		Document doc = new Document(PageSize.A4);
		//get pdf writer to write to the document and response object
		PdfWriter.getInstance(doc, res.getOutputStream());
		//open the document
		doc.open();
		//define font of the paragraph
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
		font.setSize(30);
		font.setColor(Color.RED);
		
		//create the para having content and above font style
		Paragraph para = new Paragraph(" Search Report of Course",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		//add para to the document
		doc.add(para);
		
		//dispaly the search results as pdf table
		PdfPTable tab= new PdfPTable(10);
		tab.setWidthPercentage(70);
		tab.setWidths(new float[] {5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f});
		tab.setSpacingBefore(2.0f);
		
		//prepare heading row cells int he pdf table
		PdfPCell cell= new PdfPCell();
		cell.setBackgroundColor(Color.GRAY);
		cell.setPadding(5);
		Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		cellFont.setColor(Color.BLACK);
		
		
		cell.setPhrase(new Phrase("Id",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("courseName",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("location",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("courseCategory",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("facultyName",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("adminName",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("adminContact",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("couseStatus",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("courseMode",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("startDate",cellFont));
		tab.addCell(cell);
		
		
		//add data cells to pdf cells
		lis.forEach(info->{
			tab.addCell(String.valueOf(info.getId()));
			tab.addCell(info.getCourseName());
			tab.addCell(info.getLocation());
			tab.addCell(info.getCourseCategory());
			tab.addCell(info.getFacultyName());
			tab.addCell(info.getAdminName());
			tab.addCell(String.valueOf(info.getAdminContact()));
			tab.addCell(info.getCourseStatus());
			tab.addCell(info.getCourseMode());
			tab.addCell(String.valueOf(info.getStartDate()));
		});
		
		//add table to document
		doc.add(tab);
		doc.close();
		
	}

	@Override
	public void getAllDataExcelReport(HttpServletResponse res) throws Exception {
		//get the search results
		List<CourseInfo> info1=repo.findAll();
		List<PrintData> lis = new ArrayList<PrintData>();
		info1.forEach(data->{
			PrintData p= new PrintData();
			BeanUtils.copyProperties(data, p);
			lis.add(p);
		});
		
		//create excel workbook
				HSSFWorkbook book = new HSSFWorkbook();
				
				//create sheet in work book
				HSSFSheet sheet1 = book.createSheet("Course details");
				
				//create heading row in sheet1
				HSSFRow headerRow = sheet1.createRow(0);
				headerRow.createCell(0).setCellValue("Id");
				headerRow.createCell(1).setCellValue("courseName");
				headerRow.createCell(2).setCellValue("location");
				headerRow.createCell(3).setCellValue("courseCategory");
				headerRow.createCell(4).setCellValue("facultyName");
				headerRow.createCell(5).setCellValue("adminName");
				headerRow.createCell(6).setCellValue("adminContact");
				headerRow.createCell(7).setCellValue("courseStatus");
				headerRow.createCell(8).setCellValue("courseMode");
				headerRow.createCell(9).setCellValue("startDate");
				
				//add data rows to each sheet
				int i=1;
				for(PrintData data:lis) {
					HSSFRow dataRow = sheet1.createRow(i);
							dataRow.createCell(0).setCellValue(data.getId());
							dataRow.createCell(1).setCellValue(data.getCourseName());
							dataRow.createCell(2).setCellValue(data.getLocation());
							dataRow.createCell(3).setCellValue(data.getCourseCategory());
							dataRow.createCell(4).setCellValue(data.getFacultyName());
							dataRow.createCell(5).setCellValue(data.getAdminName());
							dataRow.createCell(6).setCellValue(data.getAdminContact());
							dataRow.createCell(7).setCellValue(data.getCourseStatus());
							dataRow.createCell(8).setCellValue(data.getCourseMode());
							dataRow.createCell(9).setCellValue(data.getStartDate());
							i++;
				}
				
				//getOutputStream pointing to response obj
				ServletOutputStream ser = res.getOutputStream();
				//write the excel work book data reponse object using above stream
				book.write(ser);
				ser.close();
				book.close();
		
	}

	@Override
	public List<PrintData> getTotalPrintData() {
		//get the search results
		List<CourseInfo> info1=repo.findAll();
		List<PrintData> lis = new ArrayList<PrintData>();
		info1.forEach(data->{
			PrintData p= new PrintData();
			BeanUtils.copyProperties(data, p);
			lis.add(p);
		});
		return lis;
	}

//	@Override
//	public List<PrintData> showCourseByFilters(SearchData inputs) {
//		
//		//create a new Search Object
//		CourseInfo search = new CourseInfo();
//		//check whether the paramenterized seach obj value is valid or not
//		//checking each property in seach obj value is valid or not 
//		String category = inputs.getCourseCategory();
//		if(category.length()!=0 && !category.equals(" ") && category!=null);
//		search.setCourseCategory(category);
//		
//		String faculty = inputs.getFaculty();
//		if(faculty.length()!=0 && !faculty.equals(" ") && faculty!=null);
//		search.setFacultyName(faculty);
//		
//		String mode=inputs.getCourseMode();
//		if(mode.length()!=0 && !mode.equals(" ") && mode!=null);
//		search.setCourseMode(mode);
//		
//		LocalDateTime startDate = inputs.getStartDate();
//		if(startDate!=null)
//			search.setStartDate(startDate);
//		
//		//create a example object and pass the example object to the findAll method param
//		Example<CourseInfo> ex=Example.of(search);
//		
//		//getting all the couse details from the db table
//		List<CourseInfo> data=repo.findAll(ex);
//		//converting the db table data into the necessary data required for the report generation
//		List<PrintData> printData=new ArrayList<PrintData>();
//		data.forEach(items->{
//			PrintData print =new PrintData();
//			BeanUtils.copyProperties(items, print);
//			printData.add(print);
//		});
//		return printData;
//	}
	
	

}
