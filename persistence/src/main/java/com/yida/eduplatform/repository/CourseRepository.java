package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    public Course findByCourId(int courId);
    public List<Course> findByCourTeacher(int teaId);
    public List<Course> findByCourNameAndCourGrade(String courName, short courGrade, Pageable pageable);
    public List<Course> findByCourName(String courName, Pageable pageable);
    public List<Course> findByCourGrade(short courGrade, Pageable pageable);
    public List<Course> findAll();
}
