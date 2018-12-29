package com.yida.eduplatform.service;

import com.yida.eduplatform.domain.Student;
import com.yida.eduplatform.domain.StudentCourse;
import com.yida.eduplatform.repository.StudentCourseRepository;
import com.yida.eduplatform.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public Optional<Student> findById(long id){
        return studentRepository.findById(id);
    }

    public Student findByStuId(int stuId){
        return studentRepository.findByStuId(stuId);
    }

    public Student findTheLast(){
        List<Student> l = studentRepository.findAll();
        if(l.size()!=0){
            return l.get(0);
        }else{
            Student stu = new Student();
            stu.setStuId(1001);
            return stu;
        }
    }

    public void add(Student student){
        studentRepository.save(student);
    }

    public List<Student> findAllByCourId(int courId){
        List<StudentCourse> l = studentCourseRepository.findByCourId(courId);
        List<Student> l2 = new ArrayList<>();
        for(int i = 0; i < l.size(); i++){
            Student stu = studentRepository.findByStuId(l.get(i).getStuId());
            l2.add(stu);
        }
        return l2;
    }

    public void addStudentCourse(List<StudentCourse> studentCourses){
        studentCourseRepository.saveAll(studentCourses);
    }

}
