package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    public List<StudentCourse> findByStuId(int stuId);

    public List<StudentCourse> findByCourId(int courId);

    @Transactional
    @Modifying
    @Query("update StudentCourse sc set sc.trueCount = sc.trueCount+1 where sc.stuId=?1 and sc.courId=?2")
    public void doTrue(int stuId, int courId);

    @Transactional
    @Modifying
    @Query("update StudentCourse sc set sc.falseCount = sc.falseCount+1 where sc.stuId=?1 and sc.courId=?2")
    public void doFalse(int stuId, int courId);
}
