package com.ojt.movie.service;

import com.ojt.movie.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int signUp() throws Exception {
        return userMapper.signUp();
    }

    @Override
    public int signIn() throws Exception {
        return userMapper.signIn();
    }
}
