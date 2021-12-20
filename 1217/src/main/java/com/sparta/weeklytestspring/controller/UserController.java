package com.sparta.weeklytestspring.controller;

import com.sparta.weeklytestspring.dto.IdCheckDto;
import com.sparta.weeklytestspring.dto.JwtResponse;
import com.sparta.weeklytestspring.dto.UserDto;
import com.sparta.weeklytestspring.service.UserService;
import com.sparta.weeklytestspring.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;


@RequiredArgsConstructor
@RestController

public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    //회원 가입 - user/signup 으로 넘어온 정보를 userService로 돌려주기 -> userService는 userRepo에 저장.
    @PostMapping(value = "/user/signup")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws Exception {
        userService.registerUser(userDto);
        authenticate(userDto.getUsername(), userDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));

    }

    @PostMapping(value = "/user/idcheck")
    public Boolean idCheck(@RequestBody IdCheckDto idCheckDto) {
        return userService.toIdCheck(idCheckDto);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
//    @PostMapping("/user/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDto userDto) throws Exception {
        authenticate(userDto.getUsername(), userDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
