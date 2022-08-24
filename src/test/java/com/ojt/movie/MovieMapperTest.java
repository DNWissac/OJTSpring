package com.ojt.movie;

import com.ojt.movie.mapper.MovieMapper;
import com.ojt.movie.model.dto.MovieDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class MovieMapperTest {

    @Autowired
    MovieMapper movieMapper;

    @Test
    void movieList() {
         ArrayList<MovieDto> movieArrayList = movieMapper.selectMovieList();
    }

}
