package com.dgyj.yjpicture.config.auth;

import com.dgyj.yjpicture.web.dto.MemberDto;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserLogoutHandler implements LogoutHandler {

    @SneakyThrows
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
       if(authentication != null){
            MemberDto memberDto = (MemberDto)authentication.getPrincipal();


        }
    }
}
