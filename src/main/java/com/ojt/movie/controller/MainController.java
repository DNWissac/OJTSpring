package com.ojt.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 메인 컨트롤러
 * 페이지 이동 등을 담당하는 컨트롤러
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String mainView() {
        return "index";
    }

    @RequestMapping("/test")
    public String getMessage(Model model) {
        model.addAttribute("testStr", "타임리프 연습");
        return "testView";
    }

    /**
     * 회원가입 페이지로 이동
     * @return String 회원가입 폼으로 이동
     */
    @RequestMapping("/signupform")
    public String signupForm() {
        return "views/signupform";
    }

    /**
     * 로그인 페이지로 이동
     * @return String 로그인 페이지로 이동
     */
    @RequestMapping("/signinform")
    public String signinForm() {
        return "views/signinform";
    }

    /**
     * 영화 등록 페이지로 이동
     * @return String 영화 등록 페이지로 이동
     */
    @RequestMapping("/movieinsertform")
    public String movieinsertForm() {
        return "views/movieinsertform";
    }

    /**
     * 영화 수정 페이지로 이동
     * @return String 영화 수정 페이지로 이동
     */
    @RequestMapping("/movieupdateform")
    public String movieupdateForm() {
        return "views/movieupdateform";
    }
}
