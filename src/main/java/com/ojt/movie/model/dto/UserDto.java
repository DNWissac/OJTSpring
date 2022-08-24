package com.ojt.movie.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String sUserEmail;      // 회원 이메일
    private String sUserPassword;   // 회원 비밀번호
    private String sUserNickName;   // 회원 닉네임
    private int nUserAdmin;         // 회원 관리자 여부
    private String role;            // 회원 권한
}
