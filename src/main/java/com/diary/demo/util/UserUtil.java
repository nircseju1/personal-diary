package com.diary.demo.util;

import com.diary.demo.authentication.service.UserPrinciple;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static UserPrinciple getUser(){
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
