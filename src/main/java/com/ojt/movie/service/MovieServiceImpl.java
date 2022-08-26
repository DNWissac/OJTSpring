// MovieServiceImpl.java
package com.ojt.movie.service;

import com.ojt.movie.mapper.MovieMapper;
import com.ojt.movie.model.dto.GenreDto;
import com.ojt.movie.model.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public ArrayList<MovieDto> selectMovieList(int startNum) throws Exception {
        return movieMapper.selectMovieList(startNum);
    }

    @Override
    public int movieCount() throws Exception {
        return movieMapper.movieTotalCount();
    }

    @Override
    public ArrayList<GenreDto> genreList() throws Exception {
        return movieMapper.genreList();
    }

    @Override
    public int insertMovie(MovieDto movieDto) throws Exception {
        return movieMapper.insertMovie(movieDto);
    }

    @Override
    public MovieDto selectMovieSearch(int movieSeq) throws Exception {
        return movieMapper.selectMovieSearch(movieSeq);
    }

    @Override
    public int deleteMovie(int movieSeq) throws Exception {
        return movieMapper.deleteMovie(movieSeq);
    }

    public int updateMovie(MovieDto movieDto) throws Exception {
        return movieMapper.updateMovie(movieDto);
    }
}
