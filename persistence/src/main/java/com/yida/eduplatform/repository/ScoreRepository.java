package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.Score;
import com.yida.eduplatform.domain.ScoreMain;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.HTMLDocument;
import java.util.List;
import java.util.Map;

public interface ScoreRepository extends JpaRepository<Score,Integer>,JpaSpecificationExecutor<Score> {
    @Query("select s.score from Score s,ScoreMain sm where s.scoreMainId = sm.id and s.stuId=?1 and sm.courId=?2 order by sm.scoDate desc ")
    public List<Score> findScoreTop10BySubject(@Param("pr")Pageable pageable, @Param("stuId")int stuId, @Param("courId") int courId);

    @Query("select s.stuId,s.courId,s.courName,s.score,s.upOrDown,sm.scoDate,sm.totalScore from Score s,ScoreMain sm where s.scoreMainId = sm.id and s.stuId=?1 order by sm.scoDate desc ")
    public List<Object> findAllByStuId(@Param("pr")Pageable pageable, @Param("stuId") int stuId);

    @Query("select s.stuId,s.courId,s.courName,s.score,s.upOrDown,sm.scoDate,sm.totalScore from Score s,ScoreMain sm where s.scoreMainId = sm.id and s.stuId=?1 and s.courName=?2 order by sm.scoDate desc ")
    public List<Object> findAllByStuIdAndCour(@Param("stuId") int stuId, @Param("courName")String courName);

    @Query("select s.stuId,s.courId,s.courName,s.score,s.upOrDown,sm.scoDate,sm.totalScore from Score s,ScoreMain sm where s.scoreMainId = sm.id and s.stuId=?1 order by sm.scoDate desc ")
    public List<Object> findAllByStuId(@Param("stuId") int stuId);

    @Query("select s from Score s where s.scoreMainId=?1 order by s.score desc ")
    public List<Score> findAllByScoreMainId(@Param("courId") int mainId);

    @Query("select avg(s.score) from Score s where s.stuId=?1 and s.courId=?2")
    public Double findTheAvg(int stuId, int courId);

    @Query("select max(s.score) from Score s where s.stuId=?1 and s.courId=?2")
    public Double findTheMax(int stuId, int courId);

    @Query("select s from Score s where s.stuId=?1 and s.scoreMainId=?2")
    public Score findTheResScore(int stuId,int mainId);

    @Modifying
    @Transactional
    @Query("delete from Score s where s.scoreMainId=?1")
    public void deleteByScoreMainId(int scoreMainId);

    @Modifying
    @Transactional
    @Query("update Score s set s.score=?3,s.upOrDown=?4 where s.stuId=?1 and s.scoreMainId=?2")
    public void update(int stuId,int mainId,double score,double upOrDown);

}
