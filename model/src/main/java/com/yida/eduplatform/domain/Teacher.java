package com.yida.eduplatform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Teacher {
    @Id
    private int id;
    private int teaId;
    private String teaName;
    private byte teaSex;
    private Date teaBirthday;
    private Long teaPhone;
    private String teaEducation;
    private String teaGrade;
    private String teaPhoto;
    private String teaPassword;
    private byte isAdmin;
    private Date createTime;
    private Date updateTime;
    private byte isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public byte getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(byte teaSex) {
        this.teaSex = teaSex;
    }

    public Long getTeaPhone() {
        return teaPhone;
    }

    public void setTeaPhone(Long teaPhone) {
        this.teaPhone = teaPhone;
    }

    public String getTeaEducation() {
        return teaEducation;
    }

    public void setTeaEducation(String teaEducation) {
        this.teaEducation = teaEducation;
    }

    public String getTeaGrade() {
        return teaGrade;
    }

    public void setTeaGrade(String teaGrade) {
        this.teaGrade = teaGrade;
    }

    public String getTeaPhoto() {
        return teaPhoto;
    }

    public void setTeaPhoto(String teaPhoto) {
        this.teaPhoto = teaPhoto;
    }

    public String getTeaPassword() {
        return teaPassword;
    }

    public void setTeaPassword(String teaPassword) {
        this.teaPassword = teaPassword;
    }

    public byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(byte isAdmin) {
        this.isAdmin = isAdmin;
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

    public Date getTeaBirthday() {
        return teaBirthday;
    }

    public void setTeaBirthday(Date teaBirthday) {
        this.teaBirthday = teaBirthday;
    }
}
