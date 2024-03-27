package com.example.jwt.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public Long getStudentPhoneNum() {
        return studentPhoneNum;
    }

    public void setStudentPhoneNum(Long studentPhoneNum) {
        this.studentPhoneNum = studentPhoneNum;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    @Id
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private Long studentPhoneNum;
    private String studentEmail;
    private String studentDepartment;
    private String studentPassword;
}

