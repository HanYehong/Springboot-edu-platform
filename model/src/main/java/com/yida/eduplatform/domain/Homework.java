package com.yida.eduplatform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Homework {
    @Id
    private int id;
    private int teaId;
    private int courId;
    private String hwContent;
    private String hwSrc;
    private Date hwDeadtime;
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

    public int getCourId() {
        return courId;
    }

    public void setCourseId(int courId) {
        this.courId = courId;
    }

    public String getHwContent() {
        return hwContent;
    }

    public void setHwContent(String hwContent) {
        this.hwContent = hwContent;
    }

    public String getHwSrc() {
        return hwSrc;
    }

    public void setHwSrc(String hwSrc) {
        this.hwSrc = hwSrc;
    }

    public Date getHwDeadtime() {
        return hwDeadtime;
    }

    public void setHwDeadtime(Date hwDeadtime) {
        this.hwDeadtime = hwDeadtime;
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
        return "Homework{" +
                "id=" + id +
                ", teaId=" + teaId +
                ", courId=" + courId +
                ", hwContent='" + hwContent + '\'' +
                ", hwSrc='" + hwSrc + '\'' +
                ", hwDeadtime=" + hwDeadtime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
