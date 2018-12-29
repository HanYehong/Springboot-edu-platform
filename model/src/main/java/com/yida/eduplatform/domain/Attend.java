package com.yida.eduplatform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Attend {
    @Id
    private Long id;
    private Long stuId;
    private int courId;
    private Date attDate;
    private byte isAttend;
    private Date createTime;
    private Date updateTime;
    private byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getAttDate() {
        return attDate;
    }

    public void setAttDate(Date attDate) {
        this.attDate = attDate;
    }

    public byte getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(byte isAttend) {
        this.isAttend = isAttend;
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
