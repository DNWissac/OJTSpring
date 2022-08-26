// MovieService.java
package com.ojt.movie.service;

import com.ojt.movie.model.dto.GenreDto;
import com.ojt.movie.model.dto.MovieDto;
import java.util.ArrayList;

public interface MovieService {

    /**
     * 영화 목록을 가져오는 메서드
     * @return ArrayList<MovieDto>
     * @throws Exception
     */
    public ArrayList<MovieDto> selectMovieList(int startNum) throws Exception;

    /**
     * 장르 목록을 가져오는 메서드
     * @return ArrayList<GenreDto>
     * @throws Exception
     */
    public ArrayList<GenreDto> genreList() throws Exception;

    /**
     * 영화 총 개수를 가져오는 메서드
     * @return int
     * @throws Exception
     */
    public int movieCount() throws Exception;

    /**
     * 영화 등록 메서드
     * @param movieDto
     * @return
     * @throws Exception
     */
    public int insertMovie(MovieDto movieDto) throws Exception;

    /**
     * 영화 선택 메서드(영화 상세 정보)
     * @return
     * @throws Exception
     */
    public MovieDto selectMovieSearch(int movieSeq) throws Exception;


    /**
     * 영화 삭제 메서드
     * @param movieSeq
     * @return
     * @throws Exception
     */
    public int deleteMovie(int movieSeq) throws Exception;

    /**
     * 영화 수정 메서드
     * @param movieDto
     * @return
     * @throws Exception
     */
    public int updateMovie(MovieDto movieDto) throws Exception;
}
