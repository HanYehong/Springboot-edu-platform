package com.yida.eduplatform.service;

import com.yida.eduplatform.domain.Course;
import com.yida.eduplatform.domain.Practice;
import com.yida.eduplatform.domain.Question;
import com.yida.eduplatform.domain.StudentCourse;
import com.yida.eduplatform.repository.CourseRepository;
import com.yida.eduplatform.repository.PracticeRepository;
import com.yida.eduplatform.repository.QuestionRepository;
import com.yida.eduplatform.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PracticeService {
    @Autowired
    private PracticeRepository practiceRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CourseRepository courseRepository;

    public ArrayList<Practice> findAllByQuestion(int stuId, List<Integer> id){
        return (ArrayList<Practice>) practiceRepository.findByQuestion(stuId, id);
    }

    @Transactional
    public void add(int stuId,int quesId){
        if(practiceRepository.findByStuIdAndQuesId(stuId,quesId) == null ){
            Practice practice = new Practice();
            practice.setStuId(stuId);
            practice.setQuesId(quesId);
            practice.setFaultCount(1);
            practiceRepository.save(practice);
        }else{
            practiceRepository.updateIncre(stuId,quesId);
        }
        Optional<Question> q = questionRepository.findById(quesId);
        List<StudentCourse> l = studentCourseRepository.findByStuId(stuId);
        for( int i = 0; i < l.size(); i++ ){
            Course cour = courseRepository.findByCourId(l.get(i).getCourId());
            if(cour.getCourName().equals(q.get().getSubject()) && cour.getCourGrade() == q.get().getGrade()){
                studentCourseRepository.doFalse(stuId,cour.getCourId());
                break;
            }
        }
    }

    @Transactional
    public void remove(int stuId,int quesId){
        Practice practice = practiceRepository.findByStuIdAndQuesId(stuId,quesId);
        if( practice!=null ){
            if( practice.getFaultCount() == 1 ){
                practiceRepository.deleteById(practice.getId());
            }else{
                practiceRepository.updateDecre(stuId,quesId);
            }
        }
        Optional<Question> q = questionRepository.findById(quesId);
        List<StudentCourse> l = studentCourseRepository.findByStuId(stuId);
        for( int i = 0; i < l.size(); i++ ){
            Course cour = courseRepository.findByCourId(l.get(i).getCourId());
            if(cour.getCourName().equals(q.get().getSubject()) && cour.getCourGrade() == q.get().getGrade()){
                studentCourseRepository.doTrue(stuId,cour.getCourId());
                break;
            }
        }
    }
}
