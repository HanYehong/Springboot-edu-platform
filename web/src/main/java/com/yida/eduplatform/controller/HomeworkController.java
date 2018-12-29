package com.yida.eduplatform.controller;

import com.yida.eduplatform.domain.*;
import com.yida.eduplatform.service.CourseService;
import com.yida.eduplatform.service.HomeworkService;
import com.yida.eduplatform.service.StudentService;
import com.yida.eduplatform.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ResponseUtil responseUtil;

    @GetMapping("/homework/{stuId}")
    public ResponseUtil getHomeWork(@PathVariable("stuId")int stuId){
        try {
            List<StudentCourse> studentCourses = courseService.findByStuId(stuId);
            List<Integer> courId = new ArrayList<>();
            List<Course> courses = new ArrayList<>();
            for(int i = 0; i < studentCourses.size(); i++){
                courId.add(studentCourses.get(i).getCourId());
                courses.add(courseService.findByCourId(courId.get(i)));
            }
            List<Homework> homework = homeworkService.findByCourId((ArrayList<Integer>) courId);
            System.out.println("学生所有作业如下：");
            System.out.println(homework.toString());
            List<HomeworkDo> homeworkDos = new ArrayList<>();
            for(int i = 0; i < homework.size(); i++){
                HomeworkDo homeworkDo = homeworkService.findByStuIdAndHwId(stuId,homework.get(i).getId());
                if(homeworkDo == null) {
                    homeworkDos.add(new HomeworkDo());
                }
                else {
                    homeworkDos.add(homeworkDo);
                }
            }
            System.out.println("学生完成作业情况如下：");
            System.out.println(homeworkDos.toString());
            HashMap<String,Object> hm = new HashMap<>();
            hm.put("homework",homework);
            hm.put("homeworkDo",homeworkDos);
            hm.put("course",courses);
            responseUtil.setSuccess(true);
            responseUtil.setResult(hm);
            return responseUtil;
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            return responseUtil;
        }
    }

    @PostMapping("/homework/submit/{stuId}/{hwId}/{content}")
    public ResponseUtil submitHomework(
            @PathVariable("stuId")int stuId,@PathVariable("hwId")int hwId,@PathVariable("content")String content){
        try {
            homeworkService.addHomeworkDo(stuId,hwId,content);
            responseUtil.setSuccess(true);
            return responseUtil;
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            return responseUtil;
        }
    }

    @GetMapping("/homework/showall/{teaId}")
    public ResponseUtil findAllWorkByTeacher(@PathVariable("teaId")int teaId){
        try {
            List<Homework> homework = homeworkService.findByTeaId(teaId);
            List<Course> courses = new ArrayList<>();
            for(int i = 0; i < homework.size(); i++){
                courses.add(courseService.findByCourId(homework.get(i).getCourId()));
            }
            HashMap<String,Object> hm = new HashMap<>();
            hm.put("homework",homework);
            hm.put("course",courses);
            responseUtil.setSuccess(true);
            responseUtil.setResult(hm);
            return responseUtil;
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            return responseUtil;
        }
    }

    @GetMapping("/homework/detail/{hwId}/{courId}")
    public ResponseUtil showDetail(@PathVariable("hwId")int hwId,@PathVariable("courId")int courId){
        try {
            Optional<Homework> homework = homeworkService.findById(hwId);
            List<Student> students = studentService.findAllByCourId(courId);
            List<HomeworkDo> homeworkDos = new ArrayList<>();
            for(int i = 0; i < students.size(); i++){
                HomeworkDo homeworkDo = homeworkService.findByStuIdAndHwId(students.get(i).getStuId(),hwId);
                if(homeworkDo == null){
                    homeworkDos.add(new HomeworkDo());
                }else{
                    homeworkDos.add(homeworkDo);
                }
            }
            HashMap<String,Object> hm = new HashMap<>();
            hm.put("homeworkDo",homeworkDos);
            hm.put("homework",homework);
            hm.put("student",students);
            responseUtil.setResult(hm);
            responseUtil.setSuccess(true);
            return responseUtil;
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            return responseUtil;
        }
    }
}
