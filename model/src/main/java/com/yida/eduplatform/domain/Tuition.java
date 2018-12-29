package com.yida.eduplatform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Tuition {
    @Id
    private int id;
    private Long stuId;
    private int courId;
    private double tuition;
    private int courAdd;
    private Date tuiDate;
    private Date createTime;
    private Date updateTime;
    private byte isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public int getCourId() {
        return courId;
    }

    public void setCourId(int courId) {
        this.courId = courId;
    }

    public double getTuition() {
        return tuition;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public int getCourAdd() {
        return courAdd;
    }

    public void setCourAdd(int courAdd) {
        this.courAdd = courAdd;
    }

    public Date getTuiDate() {
        return tuiDate;
    }

    public void setTuiDate(Date tuiDate) {
        this.tuiDate = tuiDate;
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
