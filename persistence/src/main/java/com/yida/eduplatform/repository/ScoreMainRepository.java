package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.ScoreMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScoreMainRepository extends JpaRepository<ScoreMain,Integer> {
    @Query("select s from ScoreMain s where s.courId=?1 order by s.scoDate desc ")
    public List<ScoreMain> findScoreByClassAndDate(@Param("classId") int classId, @Param("pr") Pageable pageable);

    @Query("select s from ScoreMain s where s.courId=?2 and s.id<?1 order by s.id desc ")
    public List<ScoreMain> findLastId(int id,int courId, Pageable pageable);

    @Query("select s from ScoreMain s where s.courId=?2 and s.id>?1 ")
    public List<ScoreMain> findNextId(int id,int courId, Pageable pageable);
}
