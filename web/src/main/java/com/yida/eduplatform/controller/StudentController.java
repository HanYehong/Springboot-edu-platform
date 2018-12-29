package com.yida.eduplatform.controller;

import com.yida.eduplatform.domain.Student;
import com.yida.eduplatform.domain.StudentCourse;
import com.yida.eduplatform.service.StudentService;
import com.yida.eduplatform.util.ResponseUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ResponseUtil responseUtil;

    @RequestMapping("/student/show")
    public String begin(){
        return "test01";
    }

    @GetMapping("/student/{id}")
    public Optional<Student> findById(@PathVariable("id") Long id){
        return studentService.findById(id);
    }

    @GetMapping("/student/findall/{courId}")
    @ResponseBody
    public ResponseUtil findAllByCourId(@PathVariable("courId")int courId){
        List<Student> l;
        try {
            l = studentService.findAllByCourId(courId);
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("stu",l);
        responseUtil.setResult(hm);
        responseUtil.setSuccess(true);
        return responseUtil;
    }

    @PostMapping("/student/add/{name}/{sex}/{phone}")
    @ResponseBody
    @Transactional
    public ResponseUtil addStudentCourse(@RequestBody List<StudentCourse> studentCourses,
                                         @PathVariable("name")String name,@PathVariable("sex")String sex,
                                         @PathVariable("phone")String phone){
        System.out.print(studentCourses.toString());
        System.out.println(name+" "+sex+" "+phone);
        HashMap<String,Object> hm = new HashMap<>();
        try {
            Student stu = studentService.findTheLast();  //当前学号最大学生
            int stuId = stu.getStuId() + 1;    //基础上+1
            Student student = new Student();
            student.setStuId(stuId);
            student.setStuName(name);
            student.setStuSex(Byte.parseByte(sex));
            student.setStuPhone(Long.parseLong(phone));
            Md5Hash md5Hash = new Md5Hash(phone.substring(5),stuId);
            student.setStuPassword(md5Hash.toString());
            studentService.add(student);
            hm.put("username",student.getStuId());
            hm.put("password",student.getStuPassword());
            for( int i = 0; i < studentCourses.size(); i++ ){
                studentCourses.get(i).setStuId(stuId);   //设置学号
            }
            studentService.addStudentCourse(studentCourses);   //保存选课
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        responseUtil.setSuccess(true);
        responseUtil.setResult(hm);
        return responseUtil;
    }
}
