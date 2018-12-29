package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.CourseTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseTimeRepository extends JpaRepository<CourseTime, Integer> {
    public List<CourseTime> findByCourse(int courId);
}
