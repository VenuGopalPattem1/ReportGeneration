package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "COURSE_INFO")
public class CourseInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@CreationTimestamp
//	@Column(insertable = true,updatable = false)
	private LocalDateTime creationTime;
	@UpdateTimestamp
//	@Column(insertable = false,updatable = true)
	private LocalDateTime updationTime;
	
	private String createdBy;
	private String updatedBy;
	
}
