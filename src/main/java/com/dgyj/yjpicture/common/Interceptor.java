package com.dgyj.yjpicture.common;

import com.dgyj.yjpicture.web.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class Interceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        log.info("[MYTEST] preHandle");

        boolean t = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof MemberDto){
            MemberDto memberDto = (MemberDto) authentication.getPrincipal();
            log.debug("#@####@###"+memberDto.getUsername());

            String jobContent = "";
            if("/noticeList".equals(request.getRequestURI())){
                jobContent = "공지사항관리에 접속했습니다.";
                t = true;
            }else if("/mainvisualList".equals(request.getRequestURI())){
                jobContent = "메인비주얼관리에 접속했습니다.";
                t = true;
            }else if("/mainpopList".equals(request.getRequestURI())){
                jobContent = "메인팝업관리에 접속했습니다.";
                t = true;
            }else if("/qnaList".equals(request.getRequestURI())){
                jobContent = "문의하기관리에 접속했습니다.";
                t = true;
            }else if("/userList".equals(request.getRequestURI())){
                jobContent = "관리자관리에 접속했습니다.";
                t = true;
            }else if("/subsidiaryList".equals(request.getRequestURI())){
                jobContent = "계열사관리에 접속했습니다.";
                t = true;
            }else if("/prbinfoList".equals(request.getRequestURI())){
                jobContent = "개인정보처리방침관리에 접속했습니다.";
                t = true;
            }else if("/grantHistory".equals(request.getRequestURI())){
                jobContent = "권한로그관리에 접속했습니다.";
                t = true;
            }else if("/jobHistory".equals(request.getRequestURI())){
                jobContent = "작업로그관리에 접속했습니다.";
                t = true;
            }




        }

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {

        log.info("[MYTEST] postHandle");

        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires",0);
        if (request.getProtocol().equals("HTTP/1.1"))
            response.setHeader("Cache-Control", "no-cache");


    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            Exception ex
    ) throws Exception {


        log.info("[MYTEST] afterCompletion");
    }
}
