package com.ojt.movie.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

    private Integer nMovieSeq;              // 영화 번호 (PK)
    private String sMovieTitle;             // 영화 제목
    private String sMovieStory;             // 영화 내용
    private String sMovieImage;             // 영화 사진 경로
    private String dtOpeningDate;           // 영화 개봉일자
    private String dtMovieDate;             // 영화 등록일자
    private String sMovieDirector;          // 영화 감독 이름
    private String sGenreID;                // 영화 장르 ID (FK)


}
