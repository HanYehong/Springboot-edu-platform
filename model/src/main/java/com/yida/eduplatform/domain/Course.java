package com.yida.eduplatform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Entity
public class Course {
    @Id
    private int id;
    private int courId;
    private String courName;
    private Short courGrade;
    private int courTeacher;
    private Double courPrice;
    private Date createTime;
    private Date updateTime;
    private byte isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourId() {
        return courId;
    }

    public void setCourId(int courId) {
        this.courId = courId;
    }

    public String getCourName() {
        return courName;
    }

    public void setCourName(String courName) {
        this.courName = courName;
    }

    public Short getCourGrade() {
        return courGrade;
    }

    public void setCourGrade(Short courGrade) {
        this.courGrade = courGrade;
    }

    public Double getCourPrice() {
        return courPrice;
    }

    public void setCourPrice(Double courPrice) {
        this.courPrice = courPrice;
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

    public int getCourTeacher() {
        return courTeacher;
    }

    public void setCourTeacher(int courTeacher) {
        this.courTeacher = courTeacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courId=" + courId +
                ", courName='" + courName + '\'' +
                ", courGrade=" + courGrade +
                ", courTeacher=" + courTeacher +
                ", courPrice=" + courPrice +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
