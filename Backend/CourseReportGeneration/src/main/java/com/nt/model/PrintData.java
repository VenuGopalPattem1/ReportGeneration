package com.nt.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PrintData {
	private Integer Id;
	private String courseName;
	private String location;
	private String courseCategory;
	private String facultyName;
	private String adminName;
	private Long adminContact;
	private String courseStatus;
	private String courseMode;
	private LocalDateTime startDate;
}
