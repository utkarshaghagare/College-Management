package com.example.jwt.Service;

import com.example.jwt.Dao.TeacherDao;
import com.example.jwt.Model.Student;
import com.example.jwt.Model.Teacher;
import com.example.jwt.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public void addNewteacher(User user) {
        Teacher teacher= new Teacher();
        teacher.setTeacherId(user.getUserId());
        teacher.setTeacherEmail(user.getUserEmail());
        teacher.setTeacherDepartment(user.getUserDepartment());
        teacher.setTeacherFirstName(user.getUserFirstName());
        teacher.setTeacherLastName(user.getUserLastName());
        teacher.setTeacherPhoneNum(user.getUserPhoneNum());
        teacher.setTeacherPassword(user.getUserPassword());

        teacherDao.save(teacher);
    }

    public User updateTeacher(User user){
        Teacher existingTeacher  = teacherDao.findById(user.getUserId()).get();

        if(existingTeacher!= null){
            existingTeacher.setTeacherEmail(user.getUserEmail());
            existingTeacher.setTeacherDepartment(user.getUserDepartment());
            existingTeacher.setTeacherFirstName(user.getUserFirstName());
            existingTeacher.setTeacherLastName(user.getUserLastName());
            existingTeacher.setTeacherPhoneNum(user.getUserPhoneNum());
            existingTeacher.setTeacherPassword(getEncodedPassword(user.getUserPassword()));

            teacherDao.save(existingTeacher);

            return userService.updateStudentDetails(user);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid StudentDetails found. Please register Student first");

    }
    private String getEncodedPassword(String userPassword) {
        return passwordEncoder.encode(userPassword);
    }

    public List<Teacher> getAllTeachers(){
        return teacherDao.findAll();
    }
}
