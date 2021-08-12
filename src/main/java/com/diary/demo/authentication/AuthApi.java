package com.diary.demo.authentication;

import com.diary.demo.authentication.jwt.JwtProvider;
import com.diary.demo.authentication.jwt.JwtResponse;
import com.diary.demo.authentication.service.UserPrinciple;
import com.diary.demo.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginInfo loginInfo) {

        String jwt = "";
        UserDetails userDetails = null;
        UserPrinciple userPrinciple = null;
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            jwt = jwtProvider.generateJwtToken(authentication);
            userDetails = (UserDetails) authentication.getPrincipal();

        } catch (Exception ex) {

        } finally {

        }

        if (userDetails != null) {
//            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
            userPrinciple = (UserPrinciple) authentication.getPrincipal();
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(),
                    userPrinciple.getName(), userPrinciple.getEmail(), userPrinciple.getUserPhotoName(),
                    userDetails.getAuthorities()));
        } else {
            return new ResponseEntity<>(new ResponseMessage("Login Failed"), HttpStatus.UNAUTHORIZED);
        }
    }

}

