package com.ojt.movie.controller;

import com.ojt.movie.model.dto.GenreDto;
import com.ojt.movie.model.dto.MovieDto;
import com.ojt.movie.service.MovieService;
import com.ojt.movie.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.apache.commons.io.FilenameUtils.getFullPath;

/**
 * 영화 컨트롤러
 * 영화 관련한 액션 담당 컨트롤러
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * 생성자로 MovieService 삽입
     */
    MovieController() {
        this.movieService = new MovieServiceImpl();
    }

    /**
     * 영화 목록을 보여주는 메서드 (ajax 호출용 메서드)
     * @return ArrayList
     */
    @PostMapping("/list")
    @ResponseBody
    public String movieList(@RequestParam(required = false) int startNum) {

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
            movieDtoList = movieService.selectMovieList(startNum);
            int count = movieService.movieCount();

            // count key에 영화 개수 삽입
            map.put("count", count);
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

    /**
     * 영화를 등록하는 메서드
     * @param movieDto
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public String movieInsert(MovieDto movieDto) {

        // json 담을 문자열
        String result = "";

        // json 형식으로 변환하기 위해서 Gson 사용
        Gson gson = new Gson();

        // map에 넣어서 보내기용
        Map map = new HashMap();

        try {
            // 영화 제목 길이 검사
            if(movieDto.getSMovieTitle().length() < 1 || movieDto.getSMovieTitle().length() >= 50) {
                map.put("status", 400);
                map.put("msg", "영화 제목은 1 ~ 50자 입니다.");
            } else if (movieDto.getSMovieStory().length() < 1 || movieDto.getSMovieStory().length() >= 500) {
                map.put("status", 400);
                map.put("msg", "영화 내용은 1 ~ 500자 입니다.");
            } else if (movieDto.getSMovieDirector().length() < 1 || movieDto.getSMovieDirector().length() >= 50) {
                map.put("status", 400);
                map.put("msg", "영화 감독 이름은 1 ~ 50자 입니다.");
            } else if (movieDto.getImageFile().isEmpty()) {
                map.put("status", 400);
                map.put("msg", "반드시 이미지 파일을 삽입해야 합니다.");
            }
            // 모든 조건 통과
            else {
                // 사진 유효성 검사
                String imageName = movieDto.getImageFile().getOriginalFilename();
                // 확장자 분리
                int pos = imageName.lastIndexOf(".");
                String imageExt = imageName.substring(pos + 1);

                // 확장자가 jpg 또는 png가 아니라면
                if (imageExt != "jpg" || imageExt != "png") {
                    map.put("status", 400);
                    map.put("msg", "삽입할 수 있는 이미지 확장자는 jpg 또는 png입니다.");
                }

                String savePath = "C:/uploadImage/";
                String storeFileName = UUID.randomUUID().toString() + "." + imageExt;
                movieDto.getImageFile().transferTo(new File(savePath+storeFileName));

                // 이미지 이름 저장
                movieDto.setSMovieImage("/img/"+storeFileName);

                // 제대로 입력되었는지 확인
                int insertResult = movieService.insertMovie(movieDto);
                // 입력되지 않았다면
                if (insertResult != 1) {
                    map.put("status", 500);
                    map.put("msg", "입력에 실패했습니다.");
                    map.put("exception", "insertResult not 1");
                } else {
                    map.put ("status", 200);
                    map.put ("msg", "영화가 성공적으로 등록되었습니다.");
                }
            }
        } catch (Exception e) {
            map.put("status", 500);
            map.put("msg", "서버 내부 오류 발생.");
            map.put("exception", e.getMessage());
        }

        // json 변환 후 출력
        result = gson.toJson(map);
        return result;
    }

    /**
     * 장르 리스트 출력 메서드
     * @return
     */
    @PostMapping("/genreList")
    @ResponseBody
    public String genreList() {

        // 영화 리스트를 담을 배열 선언
        ArrayList<GenreDto> genreDtoList = null;

        // return 시킬 값을 담을 문자열 선언
        String result = "";

        // json 형식으로 변환하기 위해서 Gson 사용
        Gson gson = new Gson();

        // result라는 key에 ArrayList를 넣기 위해 map 선언
        Map map = new HashMap();

        try {
            // 영화 리스트를 배열에 담기
            genreDtoList = movieService.genreList();

            // result key에 장르 리스트 삽입
            map.put("result", genreDtoList);
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

    /**
     * 영화 상세정보 출력 메서드
     * @param movieSeq
     * @param model
     * @return
     */
    @GetMapping("/moviedetail")
    public String movieDetail(@RequestParam int movieSeq, Model model) {
        
        // 영화 상세정보를 담을 객체 선언
        MovieDto movieDto = new MovieDto();

        try {
            movieDto = movieService.selectMovieSearch(movieSeq);
        } catch (Exception e) {
            model.addAttribute("Exception", e);
        }

        model.addAttribute("movieDto", movieDto);
        model.addAttribute("movieSeq", movieSeq);

        return "/views/moviedetail" ;
    }


    /**
     * 영화 삭제 메서드
     * @param movieSeq
     * @return
     */
    @PostMapping("/movieDelete")
    @ResponseBody
    public String movieDelete(@RequestParam int movieSeq) {

        // json 담을 문자열
        String result = "";

        // json 형식으로 변환하기 위해서 Gson 사용
        Gson gson = new Gson();

        // map에 넣어서 보내기용
        Map map = new HashMap();

        try {
            // 제대로 삭제되었는지 확인
            int deleteResult = movieService.deleteMovie(movieSeq);
            // 삭제되지 않았다면
            if (deleteResult != 1) {
                map.put("status", 500);
                map.put("msg", "삭제에 실패했습니다.");
                map.put("exception", "deleteResult not 1");
            } else {
                map.put ("status", 200);
                map.put ("msg", "영화가 성공적으로 삭제되었습니다.");
            }

        } catch (Exception e) {
            map.put("status", 500);
            map.put("msg", "서버 내부 오류 발생.");
            map.put("exception", e.getMessage());
        }

        // json 변환 후 출력
        result = gson.toJson(map);
        return result;
    }

    /**
     * 영화 수정 페이지로 이동
     * @return String 영화 수정 페이지로 이동
     */
    @GetMapping("/movieupdateform")
    public String movieUpdateForm(@RequestParam int movieSeq, Model model) {

        MovieDto movieDto = new MovieDto();

        try {
            movieDto = movieService.selectMovieSearch(movieSeq);
        } catch (Exception e) {
            model.addAttribute("exception", e);
            return "/error";
        }

        model.addAttribute("movieDto", movieDto);
        model.addAttribute("movieSeq", movieSeq);
        return "/views/movieupdateform";
    }


    @PostMapping("/update")
    @ResponseBody
    public String movieUpdate(MovieDto movieDto) {
        // json 담을 문자열
        String result = "";

        // json 형식으로 변환하기 위해서 Gson 사용
        Gson gson = new Gson();

        // map에 넣어서 보내기용
        Map map = new HashMap();

        try {
            // 영화 제목 길이 검사
            if(movieDto.getSMovieTitle().length() < 1 || movieDto.getSMovieTitle().length() >= 50) {
                map.put("status", 400);
                map.put("msg", "영화 제목은 1 ~ 50자 입니다.");
            } else if (movieDto.getSMovieStory().length() < 1 || movieDto.getSMovieStory().length() >= 500) {
                map.put("status", 400);
                map.put("msg", "영화 내용은 1 ~ 500자 입니다.");
            } else if (movieDto.getSMovieDirector().length() < 1 || movieDto.getSMovieDirector().length() >= 50) {
                map.put("status", 400);
                map.put("msg", "영화 감독 이름은 1 ~ 50자 입니다.");
            }
            // 모든 조건 통과
            else {
                // 테스트용 이미지
                movieDto.setSMovieImage("image/hunt.jpg");
                // 제대로 입력되었는지 확인
                int insertResult = movieService.updateMovie(movieDto);
                // 입력되지 않았다면
                if (insertResult != 1) {
                    map.put("status", 500);
                    map.put("msg", "수정에 실패했습니다.");
                    map.put("exception", "insertResult not 1");
                } else {
                    map.put ("status", 200);
                    map.put ("msg", "영화가 성공적으로 수정되었습니다.");
                }
            }
        } catch (Exception e) {
            map.put("status", 500);
            map.put("msg", "서버 내부 오류 발생.");
            map.put("exception", e.getMessage());
        }

        // json 변환 후 출력
        result = gson.toJson(map);
        return result;


    }
}
