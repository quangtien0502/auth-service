package com.example.ra.controller.auth;

import com.example.ra.Service.IUserService;
import com.example.ra.model.dto.Request.User.UserLogin;
import com.example.ra.model.dto.Request.User.UserRegister;
import com.example.ra.model.dto.Response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/permit/auth")
public class AuthController {


    @Autowired
    private IUserService iUserService;
    @PostMapping("/login")
    public ResponseEntity<UserResponse> handleLogin(@Valid @RequestBody UserLogin userLogin){
        return new ResponseEntity<>(iUserService.handleLogin(userLogin), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> handleRegister(@Valid @RequestBody UserRegister userRegister){
        return new ResponseEntity<>(iUserService.handleRegister(userRegister),HttpStatus.CREATED);
    }

}
