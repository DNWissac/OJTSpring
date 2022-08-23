package com.ojt.moviescore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 영화 컨트롤러
 * 영화 관련한 액션 담당 컨트롤러
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    @PostMapping ("/list")
    public String getMovieList(Model model) {

        return "/";
    }

}
