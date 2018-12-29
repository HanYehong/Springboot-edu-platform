package com.yida.eduplatform.controller;

import com.yida.eduplatform.domain.*;
import com.yida.eduplatform.service.CourseService;
import com.yida.eduplatform.service.ScoreService;
import com.yida.eduplatform.service.StudentService;
import com.yida.eduplatform.service.TeacherService;
import com.yida.eduplatform.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ResponseUtil responseUtil;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/course/stucourse/{stuId}")
    public ResponseUtil findCourseByStu(@PathVariable("stuId") int stuId){
        List<StudentCourse> l;
        List<Course> course;
        List<List<CourseTime>> courTime;
        List<String> courseName;
        List<String> teaName;
        try {
            l = courseService.findByStuId(stuId);
            course = new ArrayList<>();
            courTime = new ArrayList<>();
            courseName = new ArrayList<>();
            teaName = new ArrayList<>();
            for( int i = 0; i < l.size(); i++ ){
                course.add(courseService.findByCourId(l.get(i).getCourId()));
                courseName.add(course.get(i).getCourName());
                teaName.add(teacherService.findByTeaId(courseService.findByCourId(course.get(i).getCourId()).getCourTeacher()).getTeaName());
                courTime.add(courseService.findCourseTime(l.get(i).getCourId()));
            }
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setMessage("查询课程失败");
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("course",course);
        map.put("stuANDcour",l);
        map.put("courseTime",courTime);
        map.put("courseName",courseName);
        map.put("teaName",teaName);
        responseUtil.setMessage("查询课程成功");
        responseUtil.setSuccess(true);
        responseUtil.setResult(map);
        return responseUtil;
    }

    @GetMapping("/course/teacourse/{teaId}")
    public ResponseUtil findCourseByTea(@PathVariable("teaId") int teaId){
        List<Course> l = courseService.findByTeaId(teaId);
        List<List<CourseTime>> courTime = new ArrayList<>();
        for( int i = 0; i < l.size(); i++ ){
            courTime.add(courseService.findCourseTime(l.get(i).getCourId()));
        }
        this.responseUtil.setSuccess(true);
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("course",l);
        hm.put("courseTime",courTime);
        this.responseUtil.setResult(hm);
        this.responseUtil.setMessage("成功查找教师所有授课信息！");
        return responseUtil;
    }

    @GetMapping("/course/student/{courId}")
    public ResponseUtil getClassStudent(@PathVariable("courId") int courId){
        List<StudentCourse> stuCour;
        List<Student> stu = new ArrayList<>();
        List<HashMap<String,Double>> score = new ArrayList<>();
        try{
            stuCour = courseService.findClassStu(courId);
            for(int i = 0; i < stuCour.size(); i++ ){
                Student s = studentService.findByStuId(stuCour.get(i).getStuId());
                stu.add(s);
                HashMap<String,Double> hash = new HashMap<>();
                Double avg = scoreService.findTheAvg((int) s.getStuId(),courId);
                hash.put("avg",avg);
                Double max = scoreService.findTheMax((int)s.getStuId(),courId);
                hash.put("max",max);
                Score sco = scoreService.findTheResScore((int) s.getStuId(),courId);
                hash.put("res",sco.getScore());
                score.add(hash);
            }
            System.out.println(stuCour.toString());
            System.out.println(stu.toString());
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            responseUtil.setMessage("查询失败");
            return responseUtil;
        }
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("classStu",stu);
        hm.put("score",score);
        responseUtil.setSuccess(true);
        responseUtil.setMessage("查询成功");
        responseUtil.setResult(hm);
        return responseUtil;
    }

    @GetMapping("/course/findall/{begin}/{pageSize}")
    public ResponseUtil findAll(
            @RequestParam("courName")String courName,@RequestParam("courGrade")String courGrade,
            @PathVariable("begin")int begin,@PathVariable("pageSize")int pageSize){
        List<Course> l;
        List<List<CourseTime>> l2 = new ArrayList<>();
        int r = 0;
        try {
            l = courseService.findAll(courName,courGrade,begin,pageSize);
            if( l.size() < pageSize ){
                r = -1;
            }else{
                List<Course> temp = courseService.findAll(courName,courGrade,begin+1,pageSize);
                System.out.println(222222);
                System.out.println(temp.toString());
                if(temp.size()<=0){
                    r = -1;
                }
            }
            for(int i = 0; i < l.size(); i++){
                List<CourseTime> courseTimes = courseService.findCourseTime(l.get(i).getCourId());
                l2.add(courseTimes);
            }
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        responseUtil.setSuccess(true);
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("course",l);
        hm.put("courseTime",l2);
        hm.put("r",r);
        responseUtil.setResult(hm);
        return responseUtil;
    }
}
