package com.ojt.movie.mapper;

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
     * @return
     */
    public int insertMovie(MovieDto movieDto);

    /**
     * 영화 상세정보 조회
     * @param movieSeq - PK
     * @return
     */
    public MovieDto selectMovieDetail(int movieSeq);

    /**
     * 영화 수정
     * @param movieDto
     * @return
     */
    public int updateMovie(MovieDto movieDto);

    /**
     * 영화 삭제
     * @param movieSeq - PK
     * @return
     */
    public int deleteMovie(int movieSeq);

    /**
     * 영화 목록
     * @return
     */
    public ArrayList<MovieDto> selectMovieList();

    /**
     * 영화 총 개수
     * @return
     */
    public int movieTotalCount();


}
