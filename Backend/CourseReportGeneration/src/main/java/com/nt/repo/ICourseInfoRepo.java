package com.nt.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.CourseInfo;

public interface ICourseInfoRepo extends JpaRepository<CourseInfo, Integer> {

	@Query("select distinct(courseCategory) from CourseInfo")
	public Set<String> getCategoryData();
	
	@Query("select distinct(facultyName) from CourseInfo")
	public Set<String> getFacultyData();
	
	@Query("select distinct(courseMode) from CourseInfo")
	public Set<String> getCourseModeData();
}
