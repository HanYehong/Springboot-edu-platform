package com.yida.eduplatform.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String subject;
    private int grade;
    private int subjectGrade;
    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    private int trueAns;
    private byte isDouble;
    private String AnsAnalyze;
    private Date createTime;
    private Date updateTime;
    private byte isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public int getTrueAns() {
        return trueAns;
    }

    public void setTrueAns(int trueAns) {
        this.trueAns = trueAns;
    }

    public byte getIsDouble() {
        return isDouble;
    }

    public void setIsDouble(byte isDouble) {
        this.isDouble = isDouble;
    }

    public String getAnsAnalyze() {
        return AnsAnalyze;
    }

    public void setAnsAnalyze(String ansAnalyze) {
        AnsAnalyze = ansAnalyze;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

    public int getSubjectGrade() {
        return subjectGrade;
    }

    public void setSubjectGrade(int subjectGrade) {
        this.subjectGrade = subjectGrade;
    }
}
