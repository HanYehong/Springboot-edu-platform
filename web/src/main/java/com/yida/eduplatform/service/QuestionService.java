package com.yida.eduplatform.service;

import com.yida.eduplatform.domain.Question;
import com.yida.eduplatform.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public void saveQuestion(List<Question> ques){
        questionRepository.saveAll(ques);
    }

    public ArrayList<Question> findAllBySubject(String courName, int courGrade){
        return (ArrayList<Question>) questionRepository.findAllBySubjectAndGrade(courName,courGrade);
    }

    public ArrayList<Question> findAll(){
        return (ArrayList<Question>) questionRepository.findAll();
    }
}
