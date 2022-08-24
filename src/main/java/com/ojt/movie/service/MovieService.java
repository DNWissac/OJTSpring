// MovieService.java
package com.ojt.movie.service;

import com.ojt.movie.model.dto.MovieDto;
import java.util.ArrayList;

public interface MovieService {

    public ArrayList<MovieDto> selectMovieList() throws Exception;

}
