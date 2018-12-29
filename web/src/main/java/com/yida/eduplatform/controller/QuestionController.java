package com.yida.eduplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yida.eduplatform.domain.Question;
import com.yida.eduplatform.service.QuestionService;
import com.yida.eduplatform.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ResponseUtil responseUtil;

    @GetMapping("/saveques")
    public ResponseUtil saveQuestion(@RequestParam("question") String questions){
        JSONArray json = JSON.parseArray(questions);
        System.out.print(json.getJSONObject(0).get("question"));
        List<Question> al = new ArrayList<>();
        for( int i = 0; i < json.size(); i++ ){
            Question qu = new Question();
            qu.setQuestion(json.getJSONObject(i).get("question").toString());
            qu.setAns1(json.getJSONObject(i).get("ans1").toString());
            qu.setAns2(json.getJSONObject(i).get("ans2").toString());
            qu.setAns3(json.getJSONObject(i).get("ans3").toString());
            qu.setAns4(json.getJSONObject(i).get("ans4").toString());
            qu.setAnsAnalyze(json.getJSONObject(i).get("ansAnalyze").toString());
            qu.setGrade(Integer.parseInt(json.getJSONObject(i).get("grade").toString()));
            qu.setIsDouble(Byte.parseByte(json.getJSONObject(i).get("isDouble").toString()));
            qu.setSubject(json.getJSONObject(i).get("subject").toString());
            qu.setTrueAns(Integer.parseInt(json.getJSONObject(i).get("trueAns").toString()));
            al.add(qu);
        }
        try {
            questionService.saveQuestion(al);
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setMessage("---保存数据错误---");
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        responseUtil.setMessage("保存数据成功");
        responseUtil.setSuccess(true);
        return responseUtil;
    }
}
