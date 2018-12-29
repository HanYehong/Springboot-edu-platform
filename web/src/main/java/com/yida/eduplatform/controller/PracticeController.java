package com.yida.eduplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yida.eduplatform.domain.Practice;
import com.yida.eduplatform.domain.Question;
import com.yida.eduplatform.service.PracticeService;
import com.yida.eduplatform.service.QuestionService;
import com.yida.eduplatform.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
public class PracticeController {
    @Autowired
    private PracticeService practiceService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ResponseUtil responseUtil;

    @GetMapping("/test/practice/findAll/{id}/{subject}/{grade}")
    public ResponseUtil findAllPra(@PathVariable("id") int stuId,@PathVariable("subject") String subject,@PathVariable("grade") String grade){
        System.out.print(subject);
        System.out.println(grade);
        String []subject_ = subject.split("-");
        String []grade_ = grade.split("-");
        ArrayList<Practice> al = new ArrayList<>();
        ArrayList<Question> al2 = new ArrayList<>();
        List<Integer> id = new ArrayList<>();
        try {
            for( int i = 0; i < subject_.length; i++ ){
                ArrayList<Question> l = questionService.findAllBySubject(subject_[i],Integer.parseInt(grade_[i]));
                al2.addAll(l);
            }
            for( int i = 0; i < al2.size(); i++ ){
                id.add(al2.get(i).getId());
            }
            System.out.println(al2.toString());
            al = practiceService.findAllByQuestion(stuId,id);
            System.out.println(al.toString());
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            responseUtil.setMessage("练习题搜索失败");
            return responseUtil;
        }
        responseUtil.setSuccess(true);
        responseUtil.setMessage("成功");
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("practice",al);
        hm.put("question",al2);
        responseUtil.setResult(hm);
        return responseUtil;
    }

    @PostMapping("/test/practice/add/{stuId}/{quesId}")
    public ResponseUtil updateAdd(@PathVariable("stuId") int stuId,@PathVariable("quesId") int quesId){
        try {
            practiceService.add(stuId,quesId);
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            responseUtil.setMessage("增加失败");
            return responseUtil;
        }
        responseUtil.setSuccess(true);
        responseUtil.setMessage("增加成功");
        return responseUtil;
    }

    @PostMapping("/test/practice/remove/{stuId}/{quesId}")
    public ResponseUtil updateRemove(@PathVariable("stuId") int stuId,@PathVariable("quesId") int quesId){
        try {
            practiceService.remove(stuId,quesId);
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            responseUtil.setMessage("删除失败");
            return responseUtil;
        }
        responseUtil.setSuccess(true);
        responseUtil.setMessage("删除成功");
        return responseUtil;
    }
}
