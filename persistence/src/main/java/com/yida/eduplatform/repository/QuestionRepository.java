package com.yida.eduplatform.repository;

import com.yida.eduplatform.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

    public List<Question> findAllBySubjectAndGrade(String subject, int grade);
}
