package com.yida.eduplatform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Student {
    @Id
    private long id;
    private int stuId;
    private String stuName;
    private byte stuSex;
    private long stuPhone;
    private String stuPassword;
    private Date createTime;
    private Date updateTime;
    private byte isDelete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public byte getStuSex() {
        return stuSex;
    }

    public void setStuSex(byte stuSex) {
        this.stuSex = stuSex;
    }

    public long getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(long stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuSex=" + stuSex +
                ", stuPhone=" + stuPhone +
                ", stuPassword='" + stuPassword + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
