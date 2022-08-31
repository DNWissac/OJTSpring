package com.ojt.movie.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieScoreDto {

    private String sScoreComment;           // 평가 내용
    private int nScore;                     // 평가 점수
    private String dtScoreDate;             // 평가 날짜
    private int nMovieSeq;                  // 평가 번호
    private String sUserNickName;           // 평가자 닉네임
}
