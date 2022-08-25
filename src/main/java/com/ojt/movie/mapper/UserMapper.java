package com.ojt.movie.mapper;

import com.ojt.movie.model.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * 회원 Mapper
 */
@Mapper
public interface UserMapper {

    /**
     * 회원가입 메서드
     * @param userDto
     * @return int
     */
    public int saveUser(UserDto userDto);

    /**
     * 로그인 메서드
     * @param sUserEmail
     * @return userDto
     */
    public UserDto signIn(String sUserEmail);

    /**
     * 이메일 중복검사 메서드
     * @return int
     */
    public int findUserEmail(String sUserEmail);

    /**
     * 닉네임 중복검사 메서드
     * @return int
     */
    public int findUserNickName(String sUserNickName);

}
