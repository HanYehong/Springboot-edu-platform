package com.yida.eduplatform.service;

import com.yida.eduplatform.domain.Teacher;
import com.yida.eduplatform.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher findByTeaId(int teaId){
        return teacherRepository.findByTeaId(teaId);
    }
}
