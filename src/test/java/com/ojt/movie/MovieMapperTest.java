package com.ojt.movie;

import com.ojt.movie.mapper.MovieMapper;
import com.ojt.movie.model.dto.MovieDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieMapperTest {

    @Autowired
    MovieMapper movieMapper;

    @Test
    void movieList() {
         List<MovieDto> movieArrayList = movieMapper.selectMovieList(1);
    }

}
