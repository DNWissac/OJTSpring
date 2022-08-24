// MovieServiceImpl.java
package com.ojt.movie.service;

import com.ojt.movie.mapper.MovieMapper;
import com.ojt.movie.model.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public ArrayList<MovieDto> selectMovieList() throws Exception {
        return movieMapper.selectMovieList();
    }
}
