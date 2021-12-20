package com.sparta.weeklytestspring.service;


import com.sparta.weeklytestspring.domain.User;
import com.sparta.weeklytestspring.dto.IdCheckDto;
import com.sparta.weeklytestspring.dto.JwtResponse;
import com.sparta.weeklytestspring.dto.UserDto;
import com.sparta.weeklytestspring.repository.UserRepository;
import com.sparta.weeklytestspring.security.UserDetailsServiceImpl;
import com.sparta.weeklytestspring.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    //회원가입
    public void registerUser(UserDto userDto) throws Exception {
        String username = userDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다");
        }
        String password = passwordEncoder.encode(userDto.getPassword());
        User user = new User(username, password);
        userRepository.save(user);
        //메소드가 끝나야 commit 이 됨.
        //이게 있으면 안됨.

    }

    public Boolean toIdCheck(IdCheckDto idCheckDto) {
        String username = idCheckDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        Boolean response = found.isPresent();
        return response;
    }


    //로그인 -> Controller 에서.

    //authenticate 인증 메서드



}
