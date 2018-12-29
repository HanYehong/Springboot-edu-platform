package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    public Teacher findByTeaId(int teaId);
}
