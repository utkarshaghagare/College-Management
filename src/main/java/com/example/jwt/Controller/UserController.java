package com.example.jwt.Controller;

import com.example.jwt.Model.Student;
import com.example.jwt.Model.Teacher;
import com.example.jwt.Model.User;
import com.example.jwt.Service.StudentService;
import com.example.jwt.Service.TeacherService;
import com.example.jwt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping("/registerNewStudent")
    public User registerNewUser(@RequestBody User user){
        return userService.newRegisterStudent(user);
    }

    @PostMapping("/registerNewTeacher")
    public User registerNewTeacher(@RequestBody User user){
        return userService.newRegisterTeacher(user);
    }

    @PutMapping("/updateStudent")
    @PreAuthorize("hasAnyRole('Admin', 'Student')")
    public User updateStudent(@RequestBody User user){
        return studentService.updateStudent(user);
    }

    @PutMapping({"/updateTeacher"})
    @PreAuthorize("hasAnyRole('Admin', 'Teacher')")
    public User updateTeacher(@RequestBody User user){
        return teacherService.updateTeacher(user);
    }

    @GetMapping("/getAllTeacher")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }


    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
