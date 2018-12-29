package com.yida.eduplatform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Exam {
    @Id
    private int id;
    private int stuId;
    private String examName;
    private String subject;
    private int grade;
    private int score;
    private Time useTime;
    private byte isPass;
    private String quesIds;
    private String ansIds;
    private String trueIds;
    private Date createTime;
    private Date updateTime;
    private byte isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Time getUseTime() {
        return useTime;
    }

    public void setUseTime(Time useTime) {
        this.useTime = useTime;
    }

    public byte getIsPass() {
        return isPass;
    }

    public void setIsPass(byte isPass) {
        this.isPass = isPass;
    }

    public String getQuesIds() {
        return quesIds;
    }

    public void setQuesIds(String quesIds) {
        this.quesIds = quesIds;
    }

    public String getAnsIds() {
        return ansIds;
    }

    public void setAnsIds(String ansIds) {
        this.ansIds = ansIds;
    }

    public String getTrueIds() {
        return trueIds;
    }

    public void setTrueIds(String trueIds) {
        this.trueIds = trueIds;
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
}
