package com.yida.eduplatform.service;

import com.yida.eduplatform.domain.Homework;
import com.yida.eduplatform.domain.HomeworkDo;
import com.yida.eduplatform.repository.HomeworkDoRepository;
import com.yida.eduplatform.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private HomeworkDoRepository homeworkDoRepository;

    public List<Homework> findByCourId(ArrayList<Integer> courId){
        return homeworkRepository.findByCourId(courId);
    }

    public List<Homework> findByTeaId(int teaId){
        return homeworkRepository.findByTeaId(teaId);
    }

    public HomeworkDo findByStuIdAndHwId(int stuId,int hwId){
        return homeworkDoRepository.findByStuIdAndAndHwId(stuId,hwId);
    }

    public void addHomeworkDo(int stuId,int hwId,String content){
        HomeworkDo homeworkDo = new HomeworkDo();
        homeworkDo.setContent(content);
        homeworkDo.setStuId(stuId);
        homeworkDo.setHwId(hwId);
        homeworkDo.setSrc("");
        homeworkDoRepository.save(homeworkDo);
    }

    public Optional<Homework> findById(int hwId){
        return homeworkRepository.findById(hwId);
    }
}
