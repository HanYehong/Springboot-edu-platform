package com.yida.eduplatform.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class CourseTime {
    @Id
    private int id;
    private int course;
    private Short courWeek;
    private Time courBegin;
    private Time courEnd;
    private byte courRank;
    private String courRoom;
    private Date createTime;
    private Date updateTime;
    private byte isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Short getCourWeek() {
        return courWeek;
    }

    public void setCourWeek(Short courWeek) {
        this.courWeek = courWeek;
    }

    public Time getCourBegin() {
        return courBegin;
    }

    public void setCourBegin(Time courBegin) {
        this.courBegin = courBegin;
    }

    public Time getCourEnd() {
        return courEnd;
    }

    public void setCourEnd(Time courEnd) {
        this.courEnd = courEnd;
    }

    public byte getCourRank() {
        return courRank;
    }

    public void setCourRank(byte courRank) {
        this.courRank = courRank;
    }

    public String getCourRoom() {
        return courRoom;
    }

    public void setCourRoom(String courRoom) {
        this.courRoom = courRoom;
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
