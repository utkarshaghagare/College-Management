package com.example.jwt.Service;

import com.example.jwt.Dao.StudentDao;
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
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public void addNewStudent(User user) {
        Student student= new Student();
        student.setStudentId(user.getUserId());
        student.setStudentEmail(user.getUserEmail());
        student.setStudentDepartment(user.getUserDepartment());
        student.setStudentFirstName(user.getUserFirstName());
        student.setStudentLastName(user.getUserLastName());
        student.setStudentPhoneNum(user.getUserPhoneNum());
        student.setStudentPassword(user.getUserPassword());

        studentDao.save(student);
    }

    public User updateStudent(User user){
        Student existingStudent  = studentDao.findById(user.getUserId()).get();

        if(existingStudent!= null){
            existingStudent.setStudentEmail(user.getUserEmail());
            existingStudent.setStudentDepartment(user.getUserDepartment());
            existingStudent.setStudentFirstName(user.getUserFirstName());
            existingStudent.setStudentLastName(user.getUserLastName());
            existingStudent.setStudentPhoneNum(user.getUserPhoneNum());
            existingStudent.setStudentPassword(getEncodedPassword(user.getUserPassword()));

            studentDao.save(existingStudent);

            return userService.updateStudentDetails(user);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid StudentDetails found. Please register Student first");

    }

    public List<Student> getAllStudents(){
        return studentDao.findAll();
    }
    private String getEncodedPassword(String userPassword) {
        return passwordEncoder.encode(userPassword);
    }

}
