package com.yida.eduplatform.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private int stuId;
    private int courId;
    private int total;
    private int remain;
    private int trueCount;
    private int falseCount;
    private Date createTime;
    private Date updateTime;
    private byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getCourId() {
        return courId;
    }

    public void setCourId(int courId) {
        this.courId = courId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
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

    public int getTrueCount() {
        return trueCount;
    }

    public void setTrueCount(int trueCount) {
        this.trueCount = trueCount;
    }

    public int getFalseCount() {
        return falseCount;
    }

    public void setFalseCount(int falseCount) {
        this.falseCount = falseCount;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", courId=" + courId +
                ", total=" + total +
                ", remain=" + remain +
                ", trueCount=" + trueCount +
                ", falseCount=" + falseCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
