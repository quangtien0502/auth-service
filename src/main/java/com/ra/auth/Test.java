package com.ra.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Value("${fullName}")
    private String fullName;
    @GetMapping("")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>(fullName, HttpStatus.OK);
    }
}
