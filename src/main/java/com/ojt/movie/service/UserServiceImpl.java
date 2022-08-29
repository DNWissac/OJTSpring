package com.ojt.movie.service;

import com.ojt.movie.mapper.UserMapper;
import com.ojt.movie.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 사용자 Service
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 회원가입 메서드
     * @param userDto
     * @return int
     * @throws Exception
     */
    @Override
    @Transactional
    public int saveUser(UserDto userDto) throws Exception {
        int checkEmail = userMapper.findUserCheck(userDto.getSUserEmail(), null);
        int checkNickName = userMapper.findUserCheck(null, userDto.getSUserNickName());
        // 이메일 중복검사
        if (checkEmail >= 1) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        // 닉네임 중복검사
        if (checkNickName >=1) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        /* 비밀번호 암호화 */
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setSUserPassword(passwordEncoder.encode(userDto.getSUserPassword()));

        return userMapper.saveUser(userDto);
    }

    @Override
    public UserDto signIn(String sUserEmail) throws NullPointerException {
        // 비밀번호가 맞는지 확인하기 위해서 UserDto에 담는다
        return userMapper.signIn(sUserEmail);
    }

    @Override
    public int emailCheck(String sUserEmail) throws Exception {
        return userMapper.findUserCheck(sUserEmail, null);
    }

    @Override
    public int nickNameCheck(String sUserNickName) throws Exception {
        return userMapper.findUserCheck(null, sUserNickName);
    }

}
