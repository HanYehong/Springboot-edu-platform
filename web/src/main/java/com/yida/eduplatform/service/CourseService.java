package com.yida.eduplatform.service;

import com.yida.eduplatform.domain.Course;
import com.yida.eduplatform.domain.CourseTime;
import com.yida.eduplatform.domain.StudentCourse;
import com.yida.eduplatform.repository.CourseRepository;
import com.yida.eduplatform.repository.CourseTimeRepository;
import com.yida.eduplatform.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private CourseTimeRepository courseTimeRepository;

    public Course findByCourId(int courId){
        return courseRepository.findByCourId(courId);
    }

    public List<StudentCourse> findByStuId(int stuId){
        return studentCourseRepository.findByStuId(stuId);
    }

    public List<StudentCourse> findClassStu(int courId){
        return studentCourseRepository.findByCourId(courId);
    }

    public List<CourseTime> findCourseTime(int courId){
        return courseTimeRepository.findByCourse(courId);
    }

    public List<Course> findByTeaId(int teaId){
        return courseRepository.findByCourTeacher(teaId);
    }

    public List<Course> findAll(String courName,String courGrade,int begin,int pageSize){
        List<Course> l = new ArrayList<>();
        Pageable pageable = PageRequest.of((begin-1)*pageSize,pageSize);
        if(courName!=null && !courName.equals("") && courGrade!=null && !courGrade.equals("")){
            l = courseRepository.findByCourNameAndCourGrade(courName,Short.parseShort(courGrade),pageable);
        }else if(courName!=null && !courName.equals("")){
            l = courseRepository.findByCourName(courName,pageable);
        }else if(courGrade!=null && !courGrade.equals("")){
            l = courseRepository.findByCourGrade(Short.parseShort(courGrade),pageable);
        }else{
            List<Course> temp = courseRepository.findAll();
            int num = 0;
            for( int i = (begin-1)*pageSize; i < temp.size() && num < pageSize; i++){
                l.add(temp.get(i));
                num++;
            }
        }
        return l;
    }

}
