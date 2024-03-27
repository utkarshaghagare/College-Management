package com.example.jwt.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public Long getTeacherPhoneNum() {
        return teacherPhoneNum;
    }

    public void setTeacherPhoneNum(Long teacherPhoneNum) {
        this.teacherPhoneNum = teacherPhoneNum;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherDepartment() {
        return teacherDepartment;
    }

    public void setTeacherDepartment(String teacherDepartment) {
        this.teacherDepartment = teacherDepartment;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    @Id
    private String teacherId;
    private String teacherFirstName;
    private String teacherLastName;
    private Long teacherPhoneNum;
    private String teacherEmail;
    private String teacherDepartment;
    private String teacherPassword;
}
