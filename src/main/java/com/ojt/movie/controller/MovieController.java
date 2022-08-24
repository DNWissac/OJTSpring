package com.ojt.movie.controller;

import com.ojt.movie.model.dto.MovieDto;
import com.ojt.movie.service.MovieService;
import com.ojt.movie.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 영화 컨트롤러
 * 영화 관련한 액션 담당 컨트롤러
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    MovieController() {
        this.movieService = new MovieServiceImpl();
    }

    /**
     * 영화 목록을 보여주는 메서드
     * @return ArrayList
     */
    @PostMapping("/list")
    @ResponseBody
    public String MovieList() {

        // 영화 리스트를 담을 배열 선언
        ArrayList<MovieDto> movieDtoList = null;
        
        // return 시킬 값을 담을 문자열 선언
        String result = "";

        // json 형식으로 변환하기 위해서 Gson 사용
        Gson gson = new Gson();

        // result라는 key에 ArrayList를 넣기 위해 map 선언
        Map map = new HashMap();

        try {
            // 영화 리스트를 배열에 담기
            movieDtoList = movieService.selectMovieList();

            // result key에 영화 리스트 삽입
            map.put("result", movieDtoList);
            // status key에 상태 코드 삽입
            map.put("status", 200);
            // msg key에 status 에 대한 설명 삽입
            map.put("msg", "에러 없이 데이터가 송신에 성공");
            
        } catch (Exception e) {
            map.put("status", 500);
            map.put("msg" , "서버 내부 Exception 발생 : " + e);
        }

        // json 형식으로 변환
        result = gson.toJson(map);
        return result;

    }

}
