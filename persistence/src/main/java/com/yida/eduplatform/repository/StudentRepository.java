package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public Student findByStuId(int StuId);

    @Query("select s from Student s order by s.stuId desc ")
    public List<Student> findAll();
}
