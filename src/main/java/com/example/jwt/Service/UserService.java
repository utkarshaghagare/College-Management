package com.example.jwt.Service;

import com.example.jwt.Dao.RoleDao;
import com.example.jwt.Dao.UserDao;
import com.example.jwt.Model.Role;
import com.example.jwt.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

//    public User registerNewUser(User user) {
//        Role role = roleDao.findById("User").get();
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(role);
//        String password = getEncodedPassword(user.getUserPassword());
//        user.setUserPassword(password);
//        user.setRole(roleSet);
//
//        return userDao.save(user);
//    }
    public User newRegisterStudent(User user){
        Role role = roleDao.findById("Student").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        String password = getEncodedPassword(user.getUserPassword());
        user.setUserPassword(password);
        user.setRole(userRoles);

        studentService.addNewStudent(user);
        return userDao.save(user);
//        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
//        user.setRole(userRoles);
//
//
//        //student table save
//        studentService.addNewStudent(user);
//        return userDao.save(user);

    }

    public User newRegisterTeacher(User user){
        Role role = roleDao.findById("Teacher").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        //teacher table save
        teacherService.addNewteacher(user);
        return userDao.save(user);

    }

    private String getEncodedPassword(String userPassword) {
        return passwordEncoder.encode(userPassword);
    }

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role studentRole = new Role();
        studentRole.setRoleName("Student");
        studentRole.setRoleDescription("Default role for student");
        roleDao.save(studentRole);

        Role teacherRole = new Role();
        teacherRole.setRoleName("Teacher");
        teacherRole.setRoleDescription("Default role for teachers");
        roleDao.save(teacherRole);

        User adminUser = new User();
        adminUser.setUserId("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserEmail("admin@gmail.com");
        adminUser.setUserDepartment("Administration");
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

//    public User updateStudent(User user) {
//        User existingUser = userDao.findById(user.getUserId()).get();
//
//        if(existingUser!= null){
//            studentService.updateStudent(User user);
//        }
//
//        if(existingUser!= null&& existingUser.getRole()=="Student"){
//            existingUser.setUserId(user.getUserId());
//            existingUser.setUserEmail(user.getUserEmail());
//            existingUser.setUserDepartment(user.getUserDepartment());
//            existingUser.setUserFirstName(user.getUserFirstName());
//            existingUser.setUserLastName(user.getUserLastName());
//            existingUser.setUserPhoneNum(user.getUserPhoneNum());
//            Role role = roleDao.findById("Student").get();
//            Set<Role> userRoles = new HashSet<>();
//            userRoles.add(role);
//            existingUser.setRole(user.setRole(););
//
//        }
//
//    }

    public User updateStudentDetails(User user) {
        User existingUser = userDao.findById(user.getUserId()).get();

        if(existingUser!= null){
            existingUser.setUserId(user.getUserId());
            existingUser.setUserEmail(user.getUserEmail());
            existingUser.setUserDepartment(user.getUserDepartment());
            existingUser.setUserFirstName(user.getUserFirstName());
            existingUser.setUserLastName(user.getUserLastName());
            existingUser.setUserPhoneNum(user.getUserPhoneNum());
            existingUser.setUserPassword(getEncodedPassword(user.getUserPassword()));

            userDao.save(existingUser);
            return existingUser;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User found");

    }
}
