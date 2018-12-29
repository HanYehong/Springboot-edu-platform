package com.yida.eduplatform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Score {
    @Id
    private int id;
    private int stuId;
    private int courId;
    private String courName;
    private int scoreMainId;
    private double score;
    private double upOrDown;
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

    public int getScoreMainId() {
        return scoreMainId;
    }

    public void setScoreMainId(int scoreMainId) {
        this.scoreMainId = scoreMainId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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

    public int getCourId() {
        return courId;
    }

    public void setCourId(int courId) {
        this.courId = courId;
    }

    public double getUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(double upOrDown) {
        this.upOrDown = upOrDown;
    }

    public String getCourName() {
        return courName;
    }

    public void setCourName(String courName) {
        this.courName = courName;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", courId=" + courId +
                ", courName='" + courName + '\'' +
                ", scoreMainId=" + scoreMainId +
                ", score=" + score +
                ", upOrDown=" + upOrDown +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
