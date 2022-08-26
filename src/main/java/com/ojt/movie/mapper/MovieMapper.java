package com.ojt.movie.mapper;

import com.ojt.movie.model.dto.GenreDto;
import com.ojt.movie.model.dto.MovieDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * 영화 Mapper
 */
@Mapper
public interface MovieMapper {

    /**
     * 영화 등록
     * @param movieDto
     * @return int
     */
    public int insertMovie(MovieDto movieDto);

    /**
     * 영화 상세정보 조회
     * @param movieSeq - PK
     * @return MovieDto
     */
    public MovieDto selectMovieSearch(int movieSeq);

    /**
     * 영화 수정
     * @param movieDto
     * @return int
     */
    public int updateMovie(MovieDto movieDto);

    /**
     * 영화 삭제
     * @param movieSeq - PK
     * @return int
     */
    public int deleteMovie(int movieSeq);

    /**
     * 영화 목록
     * @return ArrayList<MovieDto>
     */
    public ArrayList<MovieDto> selectMovieList(int startNum);

    /**
     * 영화 총 개수
     * @return int
     */
    public int movieTotalCount();

    /**
     * 장르 리스트 출력
     * @return
     */
    public ArrayList<GenreDto> genreList();



}
