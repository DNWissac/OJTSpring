package com.ojt.movie.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String sUserEmail;      // 회원 이메일

    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{8,20}", message = "패스워드는 영문자와 숫자, 특수기호가 적어도 1개 이상 포함된 8~20자의 비밀번호여야 합니다.")
    private String sUserPassword;   // 회원 비밀번호
    @Size(min = 2, max = 8, message = "이름을 2~8자 사이로 입력해주세요.")
    private String sUserNickName;   // 회원 닉네임
    private String sUserAuth;       // 회원 권한
    private String dtSignupDate;    // 회원 가입일자
}
