package com.yida.eduplatform.controller;

import com.yida.eduplatform.domain.Student;
import com.yida.eduplatform.domain.Teacher;
import com.yida.eduplatform.domain.UserAndPwd;
import com.yida.eduplatform.repository.StudentRepository;
import com.yida.eduplatform.repository.TeacherRepository;
import com.yida.eduplatform.shiro.ShiroRealm;
import com.yida.eduplatform.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping("/")
    public String entryLoginHTML(){
        return "login";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/login/check")
    @ResponseBody
    public Object checkUserAndPwd(@RequestBody UserAndPwd params) {
        Map<String,Object> map = new HashMap<>();
        System.out.println("用户名："+params.getUsername()+",密码："+params.getPassword());
//        Student student = studentRepository.findByStuId(Integer.parseInt(params.getUsername()));
//        map.put("success",true);
//        map.put("people",student);
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(params.getUsername(), params.getPassword());
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            map.put("success", false);
            map.put("message", "密码错误！");
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "用户名不存在！");
            return map;
        }
        int admin = 1;
        int student = 1;
        int teacher = 1;
        try {
            subject.checkRole("admin");
        }catch (Exception e){
 //           e.printStackTrace();
            admin = 0;
        }

        try {
            subject.checkRole("student");
        }catch (Exception e){
   //         e.printStackTrace();
            student = 0;
        }

        try {
            subject.checkRole("teacher");
        }catch (Exception e){
  //          e.printStackTrace();
            teacher = 0;
        }

        if(admin == 1){
            map.put("success", true);
            map.put("message", "管理员，欢迎你！");
        }else if(student == 1){
            Student stu = (Student) subject.getPrincipal();
            map.put("success", true);
            map.put("message", "同学，欢迎你！");
            map.put("people",stu);
        }else if(teacher == 1){
            Teacher tea = (Teacher) subject.getPrincipal();
            map.put("success", true);
            map.put("message", "老师，欢迎你！");
            map.put("people",tea);
        }else{
            map.put("success", false);
            map.put("message", "无权限访问");
        }
        return map;
    }

    @PostMapping("/logout")
    @ResponseBody
    public Object logout(){
        HashMap<String,Object> hm = new HashMap<>();
        try{
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            hm.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            hm.put("success",false);
        }
        return hm;
    }

}
