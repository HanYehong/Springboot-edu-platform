package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework,Integer> {
    @Query("select s from Homework s where s.courId in ?1 order by s.createTime desc ")
    public List<Homework> findByCourId(ArrayList<Integer> courId);
    public List<Homework> findByTeaId(int teaId);
}
