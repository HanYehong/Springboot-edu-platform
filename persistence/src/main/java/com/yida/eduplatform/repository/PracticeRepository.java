package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PracticeRepository extends JpaRepository<Practice,Integer> {
    public List<Practice> findAllByStuId(int stuId);

    public Practice findByStuIdAndQuesId(int stuId, int quesId);

    @Query("select p from Practice p where stuId = ?1 and p.quesId in ?2")
    public List<Practice> findByQuestion(int stuId, List<Integer> id);

    @Transactional
    @Modifying
    @Query("update Practice p set p.faultCount = p.faultCount+1 where p.stuId=?1 and p.quesId=?2")
    public void updateIncre(int stuId,int quesId);

    @Transactional
    @Modifying
    @Query("update Practice p set p.faultCount = p.faultCount-1 where p.stuId=?1 and p.quesId=?2")
    public void updateDecre(int stuId,int quesId);

}
