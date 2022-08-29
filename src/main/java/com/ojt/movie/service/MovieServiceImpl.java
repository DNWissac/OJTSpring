// MovieServiceImpl.java
package com.ojt.movie.service;

import com.ojt.movie.mapper.MovieMapper;
import com.ojt.movie.model.dto.GenreDto;
import com.ojt.movie.model.dto.MovieDto;
import com.ojt.movie.model.dto.MovieScoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;

    @Override
    public List<MovieDto> selectMovieList(int startNum) throws Exception {
        return movieMapper.selectMovieList(startNum);
    }

    @Override
    public int movieCount() throws Exception {
        return movieMapper.movieTotalCount();
    }

    @Override
    public List<GenreDto> genreList() throws Exception {
        return movieMapper.genreList();
    }

    @Override
    public int insertMovie(MovieDto movieDto) throws Exception {
        // 사진 유효성 검사
        String imageName = movieDto.getImageFile().getOriginalFilename();
        System.out.println(imageName);
        // 확장자 분리
        int pos = imageName.lastIndexOf(".");
        String imageExt = imageName.substring(pos + 1);
        // 확장자가 jpg 또는 png가 아니라면
        if ((!imageExt.equals("jpg")) && (!imageExt.equals("png")))
            return -1;
        String savePath = "C:/uploadImage/";
        String storeFileName = UUID.randomUUID().toString() + "." + imageExt;
        movieDto.getImageFile().transferTo(new File(savePath+storeFileName));
        // 이미지 이름 저장
        movieDto.setSMovieImage("/img/"+storeFileName);
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
        // 사진 유효성 검사
        String imageName = movieDto.getImageFile().getOriginalFilename();
        // 확장자 분리
        int pos = imageName.lastIndexOf(".");
        String imageExt = imageName.substring(pos + 1);
        // 확장자가 jpg 또는 png가 아니라면
        if ((!imageExt.equals("jpg")) && (!imageExt.equals("png")))
            return -1;
        String savePath = "C:/uploadImage/";
        String storeFileName = UUID.randomUUID().toString() + "." + imageExt;
        movieDto.getImageFile().transferTo(new File(savePath+storeFileName));
        // 이미지 이름 저장
        movieDto.setSMovieImage("/img/"+storeFileName);
        return movieMapper.updateMovie(movieDto);
    }

    @Override
    public List<MovieScoreDto> movieScoreList(int nMovieSeq) {
        return movieMapper.movieScoreList(nMovieSeq);
    }
}
