package com.yida.eduplatform.service;

import com.yida.eduplatform.domain.*;
import com.yida.eduplatform.repository.CourseRepository;
import com.yida.eduplatform.repository.ScoreMainRepository;
import com.yida.eduplatform.repository.ScoreRepository;
import com.yida.eduplatform.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionScoped;
import java.util.*;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private ScoreMainRepository scoreMainRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private CourseRepository courseRepository;

    public List<Score> findScoreBySubject(int stuId, int courId){
        Pageable pager = PageRequest.of(0,10);
        return scoreRepository.findScoreTop10BySubject(pager,stuId, courId);
    }

    public List<Object> findAll(int stuId,int begin,int end){
        Pageable pager = PageRequest.of(begin,end);
        return scoreRepository.findAllByStuId(pager,stuId);
    }

    public List<Object> findAllByStuIdAndCour(int stuId,String courName){
        return scoreRepository.findAllByStuIdAndCour(stuId,courName);
    }

    public List<Object> findAll(int stuId){
        return scoreRepository.findAllByStuId(stuId);
    }

    public List<ScoreMain> findScoreByClassAndDate(int classId, int date){
        Pageable pageable = PageRequest.of(date,1);
        return scoreMainRepository.findScoreByClassAndDate(classId,pageable);
    }

    public List<Score> findAllByScoreMainId(int mainId){
        List<Score> l = scoreRepository.findAllByScoreMainId(mainId);  //得到所有本次考试所有成绩
        Optional<ScoreMain> sm = scoreMainRepository.findById(mainId);
        Course course = courseRepository.findByCourId(sm.get().getCourId());
        List<StudentCourse> l2 = studentCourseRepository.findByCourId(sm.get().getCourId()); //得到该班所有学生
        if(l.size() != l2.size()){    //若个数不一样 说明有个别考生成绩未录入 末尾追加考生信息 并默认成绩为0
            List<Integer> stuId = new ArrayList<>();  //存l中存在的考生ID
            for( int i = 0; i < l.size(); i++ ){
                stuId.add(l.get(i).getStuId());
            }
            for( int j = 0; j < l2.size(); j++ ){
                if (!stuId.contains(l2.get(j).getStuId())){  //若l中不包含l2中的某考生ID 追加
                    Score score = new Score();
                    score.setStuId(l2.get(j).getStuId());
                    score.setScore(0.0);       //默认成绩为0
                    score.setCourId(course.getCourId());
                    score.setCourName(course.getCourName());
                    score.setScoreMainId(mainId);
                    l.add(score);
                }
            }
        }
        return l;
    }

    public Double findTheAvg(int stuId,int courId){
        Double avg = scoreRepository.findTheAvg(stuId,courId);
        if(avg == null) return 0.0;
        return avg;
    }

    public Double findTheMax(int stuId,int courId){
        Double max = scoreRepository.findTheMax(stuId,courId);
        if(max == null) return 0.0;
        return max;
    }

    public Score findTheResScore(int stuId,int courId){
        Pageable pageable = PageRequest.of(0,1);
        List<ScoreMain> l = scoreMainRepository.findScoreByClassAndDate(courId,pageable);
        if( l.size() == 0 ){
            return new Score();
        }else{
            int id = l.get(0).getId();
            Score score = scoreRepository.findTheResScore(stuId,id);
            if( score == null ){
                return new Score();
            }
            return score;
        }
    }

    @Transactional
    public void update(int stuId,int courId,int date,double score){

        Pageable p= PageRequest.of(date,1);    //分页取，index为要更新的mainId下标
        List<ScoreMain> list = scoreMainRepository.findScoreByClassAndDate(courId,p);  //得到mainId
        int mainId = list.get(0).getId();

        Pageable pageable = PageRequest.of(0,1);
        List<ScoreMain> t = scoreMainRepository.findLastId(mainId,courId,pageable);  //查找上一个mainId

        List<ScoreMain> t2 = scoreMainRepository.findNextId(mainId,courId,pageable);  //查找下一个mainId

        double lastScore;       //上一次成绩
        if( t.size() == 0 ){  //没有上一次成绩则默认为0
            lastScore = 0;
        }else{
            lastScore = (scoreRepository.findTheResScore(stuId,t.get(0).getId())).getScore();
        }

        if(scoreRepository.findTheResScore(stuId,mainId) == null ){   //如果本次更新的成绩不存在（为0 从未录入）
            Course course = courseRepository.findByCourId(courId);   //得到课程信息
            Score s = new Score();   //新建成绩对象
            s.setStuId(stuId);
            s.setScoreMainId(mainId);
            s.setScore(score);
            s.setCourName(course.getCourName());
            s.setCourId(course.getCourId());
            s.setUpOrDown(score - lastScore);
            scoreRepository.save(s);
        }else{            //若成绩存在 直接更新
            double upOrDown = score - lastScore;  //更新波动分数
            scoreRepository.update(stuId,mainId,score,upOrDown);
        }
        if(t2.size()!=0){   //若存在下一次成绩 则更新下一次成绩的波动分数
            int ma = t2.get(0).getId();
            Score next = scoreRepository.findTheResScore(stuId,ma);
            if(next != null){
                double upOrDown = next.getScore() - score;
                scoreRepository.update(stuId,ma,next.getScore(),upOrDown);
            }
        }
    }

    public void add(Score score, int resId){
        if( resId == -1 ){
            score.setUpOrDown(0);   //设置波动分数
            scoreRepository.save(score);  //插入
        }else{
            Score temp = scoreRepository.findTheResScore(score.getStuId(),resId);  //得到上一次成绩
            if( temp == null ){
                score.setUpOrDown((score.getScore() - 0));
            }else{
                score.setUpOrDown((score.getScore() - temp.getScore()));   //设置波动分数
            }
            scoreRepository.save(score);  //插入
        }
    }

    public HashMap<String,Object> addMain(ScoreMain scoreMain){
        Pageable pageable = PageRequest.of(0,1);
        List<ScoreMain> l = scoreMainRepository.findScoreByClassAndDate(scoreMain.getCourId(),pageable);
        int resId;
        if(l.size() == 0){
            resId = -1;
        }else{
            resId = l.get(0).getId();   //得到上一次成绩mainId
        }
        scoreMainRepository.save(scoreMain);
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("lastId",resId);
        hm.put("id",(scoreMainRepository.findScoreByClassAndDate(scoreMain.getCourId(),pageable)).get(0).getId());
        return hm;
    }

    @Transactional
    public void delete(int courId,int date){
        Pageable pageable = PageRequest.of(date,1);
        List<ScoreMain> scoreMain = scoreMainRepository.findScoreByClassAndDate(courId,pageable);
        if( scoreMain!=null ){
            int mainId = scoreMain.get(0).getId();
            scoreRepository.deleteByScoreMainId(mainId);   //删除scoreMain
            scoreMainRepository.deleteById(mainId);   //删除Score
            /**
             * 以下操作 更新学生波动分数
             */
            List<StudentCourse> l = studentCourseRepository.findByCourId(courId);
            Pageable p = PageRequest.of(0,1);
            List<ScoreMain> next = scoreMainRepository.findNextId(mainId,courId,p);
            if( next.size()!=0 ){  //若存在后一次考试成绩 则更新后一次的波动分数
                int nextId = next.get(0).getId();
                for(int i = 0; i < l.size(); i++){
                    Score score = scoreRepository.findTheResScore(l.get(i).getStuId(),nextId);
                    update(l.get(i).getStuId(),courId,date-1,score.getScore());
                }
            }
        }
    }
}
