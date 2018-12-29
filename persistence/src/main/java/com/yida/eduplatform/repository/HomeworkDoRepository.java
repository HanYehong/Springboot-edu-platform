package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.Homework;
import com.yida.eduplatform.domain.HomeworkDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkDoRepository extends JpaRepository<HomeworkDo,Integer> {
    public HomeworkDo findByStuIdAndAndHwId(int stuId,int hwId);
}
