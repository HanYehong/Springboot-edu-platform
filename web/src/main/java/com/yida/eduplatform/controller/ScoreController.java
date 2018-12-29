package com.yida.eduplatform.controller;

import com.yida.eduplatform.domain.Score;
import com.yida.eduplatform.domain.ScoreMain;
import com.yida.eduplatform.domain.Student;
import com.yida.eduplatform.service.ScoreService;
import com.yida.eduplatform.service.StudentService;
import com.yida.eduplatform.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ResponseUtil responseUtil;
    @Autowired
    private StudentService studentService;

    @GetMapping("/score")
    public ResponseUtil showScore(@RequestParam(value = "kc[]") String kc[],@RequestParam(value = "stuId")int stuId){
        System.out.println("========/score========");
        List<List<Score>> l = new ArrayList<>();
        List<String> subject = Arrays.asList(kc);
        try {
            for( int i = 0; i < subject.size(); i++ ){
                List<Score> tempL = findScoreBySubjectTop10(stuId, Integer.parseInt(subject.get(i)));
                l.add(tempL);
            }
        }catch (Exception e){
            responseUtil.setSuccess(false);
            responseUtil.setMessage("查询失败");
            return responseUtil;
        }
        System.out.print(l.toString());
        HashMap<String,Object> map = new HashMap<>();
        map.put("score",l);
        responseUtil.setSuccess(true);
        responseUtil.setMessage("查询前10条成绩成功");
        responseUtil.setResult(map);
        return responseUtil;
    }

    @GetMapping("/score/{stuId}/{courId}")
    public List<Score> findScoreBySubjectTop10(@PathVariable("stuId") int stuId, @PathVariable("courId") int courId){
        System.out.println("========/score/studId/courId========");
        List<Score> l;
        try{
            l = scoreService.findScoreBySubject(stuId, courId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return l;
    }

    @GetMapping("/score/findAll/{stuId}")
    public ResponseUtil findAll(@PathVariable("stuId") int stuId,@RequestParam("courName")String name){
        List<Object> l;
        System.out.print("name==="+name);
        try{
            if( name == null || name.equals("")){
                System.out.println(11111);
                l = scoreService.findAll(stuId);
            }
            else{
                System.out.println(2222);
                l = scoreService.findAllByStuIdAndCour(stuId,name);
            }
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setMessage("查询失败");
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        System.out.print(l.toString());
        responseUtil.setMessage("查询成功");
        responseUtil.setSuccess(true);
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("score",l);
        responseUtil.setResult(hm);
        return responseUtil;
    }

    @GetMapping("/score/class/{classId}/{date}")
    public ResponseUtil findClassScore(@PathVariable("classId")String classId,@PathVariable("date")int date){
        List<ScoreMain> sm;
        List<Score> l;
        List<Student> stu = new ArrayList<>();
        int r = 0;
        try {
            sm = scoreService.findScoreByClassAndDate(Integer.parseInt(classId),date);
            System.out.println("sm.size()========="+sm.size());
            if(scoreService.findScoreByClassAndDate(Integer.parseInt(classId),date+1).size()<=0){
                r = -1;
            }
            int courId = sm.get(0).getId();
            System.out.println("courId====="+courId);
            l = scoreService.findAllByScoreMainId(courId);
            for( int i = 0; i < l.size(); i++ ){
                Student s = studentService.findByStuId(l.get(i).getStuId());
                stu.add(s);
            }
            System.out.println(l.toString());
            System.out.println("l.size()==="+l.size());
            System.out.println("r==="+r);
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            responseUtil.setMessage("查询失败");
            return responseUtil;
        }
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("classScore",l);
        hm.put("classStudent",stu);
        hm.put("r",r);
        hm.put("scoreMain",sm.get(0));
        responseUtil.setResult(hm);
        responseUtil.setSuccess(true);
        responseUtil.setMessage("查询成功");
        return responseUtil;
    }

    @PostMapping("/score/update/{stuId}/{courId}/{index}/{score}")
    public ResponseUtil update(@PathVariable("stuId")int stuId,@PathVariable("courId")int courId,@PathVariable("index")int date,@PathVariable("score") double score){
        try {
            scoreService.update(stuId,courId,date,score);
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            responseUtil.setMessage("更新失败");
            return responseUtil;
        }
        responseUtil.setSuccess(true);
        responseUtil.setMessage("更新成功");
        return responseUtil;
    }

    @PostMapping("/score/add/{lastId}")
    public ResponseUtil add(@RequestBody Score score,@PathVariable("lastId")int lastMainId){
        System.out.println(score.toString());
        try{
            scoreService.add(score,lastMainId);
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setMessage("失败");
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        responseUtil.setMessage("成功");
        responseUtil.setSuccess(true);
        return responseUtil;
    }

    @PostMapping("/score/addmain")
    public ResponseUtil addMain(@RequestBody ScoreMain scoreMain){
        System.out.println("==============================================1111");
        System.out.println(scoreMain.toString());
        HashMap<String,Object> hm;
        int id;
        try {
            hm = scoreService.addMain(scoreMain);
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        responseUtil.setSuccess(true);
        responseUtil.setResult(hm);
        return responseUtil;
    }

    @PostMapping("/score/delete/{courId}/{date}")
    public ResponseUtil delete(@PathVariable("courId")int courId,@PathVariable("date")int date){
        try {
            scoreService.delete(courId,date);
        }catch (Exception e){
            e.printStackTrace();
            responseUtil.setSuccess(false);
            return responseUtil;
        }
        responseUtil.setSuccess(true);
        return responseUtil;
    }
}
