// MovieService.java
package com.ojt.movie.service;

import com.ojt.movie.model.dto.MovieDto;
import java.util.ArrayList;

public interface MovieService {

    /**
     * 영화 목록을 가져오는 메서드
     * @return ArrayList<MovieDto>
     * @throws Exception
     */
    public ArrayList<MovieDto> selectMovieList() throws Exception;

    /**
     * 영화 총 개수를 가져오는 메서드
     * @return int
     * @throws Exception
     */
    public int movieCount() throws Exception;



}
