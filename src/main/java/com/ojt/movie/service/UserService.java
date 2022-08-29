package com.ojt.movie.service;

import com.ojt.movie.model.dto.UserDto;
import org.apache.ibatis.jdbc.Null;

public interface UserService {

    /**
     * 회원가입 메서드
     * @param userDto
     * @return int
     * @throws Exception
     */
    public int saveUser(UserDto userDto) throws Exception;

    /**
     * 로그인 메서드
     * @return int
     * @throws Exception
     */
    public UserDto signIn(String sUserEmail) throws NullPointerException;

    /**
     * 이메일 중복검사용 메서드
     * @param sUserEmail
     * @return int
     * @throws Exception
     */
    public int emailCheck(String sUserEmail) throws Exception;

    /**
     * 닉네임 중복검사용 메서드
     * @param sUserNickName
     * @return int
     * @throws Exception
     */
    public int nickNameCheck(String sUserNickName) throws Exception;

}
