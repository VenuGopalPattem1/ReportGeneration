package com.nt.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchData {
	private String courseCategory;
	private String faculty;
	private String courseMode;
	private LocalDateTime startDate;

}
