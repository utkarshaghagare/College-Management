package com.example.jwt.Service;
//
//import com.example.jwt.Dao.UserDao;
//import com.example.jwt.Model.JwtRequest;
//import com.example.jwt.Model.JwtResponse;
//import com.example.jwt.Model.User;
//import com.example.jwt.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class JwtService implements UserDetailsService {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    //why need to create token???
//    /* if user is new he will register
//     if he register then he will be there in user database
//     then whenever he needs to acesss services then only he will generate token
//     */
//
//    /* this method take jwt request as in username and userpassword to creat token
//    1. authnticate user - in build - authenticationManager
//    2. generate token if user is authenticated
//    Util-> token generatetoken(userdetails) ->  userdetails LoaduserbyUsername(username)-> username is given in jwt request made
//    3. find user from userdao
//     4. make jwt response(user, token)
//     */
//    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
//        String username= jwtRequest.getUserName();
//        String userpassword= jwtRequest.getUserPassword();
//        authenticate(username,userpassword);
//
//        UserDetails userDetails= loadUserByUsername(username);
//        String newGeneratedToken= jwtUtil.generateToken(userDetails);
//        User user= userDao.findById(username).get();
//
//        return new JwtResponse(user,newGeneratedToken);
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    User user = userDao.findById(username).get();
//
//    if(user!= null){
//        return new org.springframework.security.core.userdetails.User(
//                user.getUserId(),
//                user.getUserPassword(),
//                getAuthorities(user)
//        );
//    }
//    else{
//        throw new UsernameNotFoundException("User not found with username");
//    }
//
//    }
//
//    private Set getAuthorities(User user) {
//        Set<SimpleGrantedAuthority> authorities= new HashSet<>();
//        user.getRole().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        });
//        return authorities;
//    }
//
//    private void authenticate(String userName, String userpassword) throws Exception{
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userpassword));
//        }
//        catch(DisabledException e){
//            throw new Exception("User Disabled",e);
//        }
//        catch(BadCredentialsException e){
//            throw new Exception("Invalid Credentials", e);
//        }
//
//    }
//
//}
//package com.youtube.jwt.service;

import com.example.jwt.Dao.UserDao;
import com.example.jwt.Model.JwtRequest;
import com.example.jwt.Model.JwtResponse;
import com.example.jwt.Model.User;
import com.example.jwt.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userDao.findById(userName).get();
        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findById(username).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserId(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
