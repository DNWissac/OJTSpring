// MovieServiceImpl.java
package com.ojt.movie.service;

import com.ojt.movie.mapper.MovieMapper;
import com.ojt.movie.model.dto.GenreDto;
import com.ojt.movie.model.dto.MovieDto;
import com.ojt.movie.model.dto.MovieScoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * MovieService 인터페이스를 상속받는 클래스
 */
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;

    /**
     * 영화 리스트
     * @param startNum
     * @return
     * @throws Exception
     */
    @Override
    public List<MovieDto> selectMovieList(int startNum) throws Exception {
        return movieMapper.selectMovieList(startNum);
    }

    /**
     * 영화 갯수(페이징)
     * @return
     * @throws Exception
     */
    @Override
    public int movieCount() throws Exception {
        return movieMapper.movieTotalCount();
    }

    /**
     * 장르 리스트
     * @return
     * @throws Exception
     */
    @Override
    public List<GenreDto> genreList() throws Exception {
        return movieMapper.genreList();
    }

    /**
     * 영화 삽입
     * @param movieDto
     * @return
     * @throws Exception
     */
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

    /**
     * 영화 찾기
     * @param movieSeq
     * @return
     * @throws Exception
     */
    @Override
    public MovieDto selectMovieSearch(int movieSeq) throws Exception {
        return movieMapper.selectMovieSearch(movieSeq);
    }

    /**
     * 영화 삭제
     * @param movieSeq
     * @return
     * @throws Exception
     */
    @Override
    public int deleteMovie(int movieSeq) throws Exception {
        return movieMapper.deleteMovie(movieSeq);
    }

    /**
     * 영화 수정
     * @param movieDto
     * @return
     * @throws Exception
     */
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

    /**
     * 영화 점수 리스트
     * @param nMovieSeq
     * @return
     */
    @Override
    public List<MovieScoreDto> movieScoreList(int nMovieSeq) {
        return movieMapper.movieScoreList(nMovieSeq);
    }

    /**
     * 영화 점수 총점
     * @param nMovieSeq
     * @return
     */
    @Override
    public int movieScoreSum(int nMovieSeq) {
        return movieMapper.movieScoreSum(nMovieSeq);
    }

    /**
     * 영화 점수 갯수
     * @param nMovieSeq
     * @return
     */
    @Override
    public int movieScoreCount(int nMovieSeq) {
        return movieMapper.movieScoreCount(nMovieSeq);
    }
}
