package com.example.ra.Service.Imp;

import com.example.ra.Service.IRoleService;
import com.example.ra.Service.IUserService;
import com.example.ra.model.dto.Request.User.UserLogin;
import com.example.ra.model.dto.Request.User.UserRegister;
import com.example.ra.model.dto.Response.UserResponse;
import com.example.ra.model.entity.Role;
import com.example.ra.model.entity.User;
import com.example.ra.repository.UserRepository;
import com.example.ra.security.jwt.JwtProvider;
import com.example.ra.security.user_principle.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public UserResponse handleLogin(UserLogin userLogin) {
        Authentication authentication;
        try{
            authentication=authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(),userLogin.getPassword()));
        }catch (AuthenticationException authenticationException){
            throw new RuntimeException("UserName or PassWord Wrong");
        }
        UserPrinciple userPrinciple= (UserPrinciple) authentication.getPrincipal();
        String token=jwtProvider.generateToken(userPrinciple);
        return UserResponse.builder()
                .fullName(userPrinciple.getUser().getFullName())
                .userName(userPrinciple.getUser().getUserName())
                .status(userPrinciple.getUser().getStatus())
                .id(userPrinciple.getUser().getId()).accessToken(token)
                .roles(userPrinciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .email(userPrinciple.getUser().getEmail())
                .build();

    }

    @Override
    public String handleRegister(UserRegister userRegister) {
        if(userRepository.existsByUserName(userRegister.getUserName())){
            throw new RuntimeException("user name is exists");
        }
        Set<Role> roles=new HashSet<>();
        roles.add(roleService.findByRoleName("ROLE_USER"));
        User user= User.builder().fullName(userRegister.getFullName()).userName(userRegister.getUserName()).password(passwordEncoder.encode(userRegister.getPassword())).status(true).roles(roles).build();
        userRepository.save(user);
        return "Success";
    }





}
