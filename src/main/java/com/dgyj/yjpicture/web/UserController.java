package com.dgyj.yjpicture.web;

import com.dgyj.yjpicture.domain.user.Member;
import com.dgyj.yjpicture.web.dto.MemberDto;
import com.dgyj.yjpicture.web.dto.SearchDto;
import com.dgyj.yjpicture.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private LocaleResolver localeResolver;

    private final UserService userService;

    //로그아웃 결과 페이지
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    //로그인 페이지 이동
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("isLocked",request.getAttribute("isLocked"));
        model.addAttribute("isLockedMsg",request.getAttribute("isLockedMsg"));
        return "content/login/login";
    }

    //회원가입 페이지 이동
    @GetMapping(value = "/userInfo")
    public String userInfo(@ModelAttribute Member member, @ModelAttribute SearchDto searchDto, Model model, @AuthenticationPrincipal MemberDto memberDto) {

        Member user = userService.getUser(member);
        model.addAttribute("searchDto",searchDto);
        model.addAttribute("member",user);
        return "content/userInfo";
    }

    //회원가입 처리
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute Member user,@AuthenticationPrincipal MemberDto memberDto, HttpServletRequest request, HttpServletResponse response) throws Exception{
        userService.joinUser(user, memberDto, request);
        return "redirect:/userList";
    }

    //회원정보 수정 처리
    @PostMapping(value="/updateUser")
    public String updateUser(@ModelAttribute Member user,@AuthenticationPrincipal MemberDto memberDto, HttpServletRequest request, HttpServletResponse response) throws Exception{
        userService.updateUser(user, memberDto, request);
        return "redirect:/userList";
    }

    //로그인 결과 페이지
//    @GetMapping("/loginSuccess")
//    public String loginSuccess(@AuthenticationPrincipal MemberDto memberDto){
//        return "redirect:/main";
//    }

    @GetMapping("/userList")
    public String userList(@AuthenticationPrincipal MemberDto memberDto, @ModelAttribute SearchDto searchDto, Model model){
        model.addAttribute("searchDto",searchDto);
        return "content/userList";
    }

    @RequestMapping(value="/logoutSuccess")
    public String logoutSuccess(@AuthenticationPrincipal MemberDto memberDto){
        return "redirect:/login";
    }
}


