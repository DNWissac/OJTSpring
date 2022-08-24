package com.ojt.movie.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 회원 Mapper
 */
@Mapper
public interface UserMapper {

    public int signUp();

    public int signIn();

}
