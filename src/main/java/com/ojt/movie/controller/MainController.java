package com.ojt.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 메인 컨트롤러
 * 페이지 이동 등을 담당하는 컨트롤러
 */
@RequestMapping("/")
@Controller
public class MainController {

    @GetMapping("")
    public String mainView() {
        return "/index";
    }

    @GetMapping("index")
    public String getMessage() {
        return "/index";
    }

    /**
     * 회원가입 페이지로 이동
     * @return String 회원가입 폼으로 이동
     */
    @GetMapping("signupform")
    public String signupForm() {
        return "/views/signup/signupform";
    }

    /**
     * 로그인 페이지로 이동
     * @return String 로그인 페이지로 이동
     */
    @GetMapping("signinform")
    public String signinForm() {
        return "/views/signinform";
    }

    /**
     * 영화 등록 페이지로 이동
     * @return String 영화 등록 페이지로 이동
     */
    @GetMapping("movieinsertform")
    public String movieinsertForm() {
        return "/views/movieinsertform";
    }

}
