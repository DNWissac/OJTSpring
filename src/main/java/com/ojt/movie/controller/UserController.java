package com.ojt.movie.controller;

import com.ojt.movie.model.dto.UserDto;
import com.ojt.movie.service.UserService;
import com.ojt.movie.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 생성자로 userService 삽입
     */
    UserController() { this.userService = new UserServiceImpl(); }


    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public String signUp(UserDto userDto, Model model) {

        // 회원가입 유효성 검사


        // userService.saveUser()가 Exception을 발생시킬 수 있기 때문에 try~catch
        try {
            int result = userService.saveUser(userDto);

            // 회원가입이 올바르게 된 경우
            if (result == 1) {
                return "/views/signup/signup_succes";
            }
            
            // 회원가입이 실패한 경우
            else {
                // 실패 페이지로 이동
                return "/views/signup/signup_fail";
            }
        }
        // 예외가 발생한 경우
        catch (Exception e) {
            // 에러 콘솔 확인용
            System.out.println("Exception 발생 : " + e);
            model.addAttribute("exception", e.getMessage());
            // 실패 페이지로 이동
            return "/views/signup/signup_fail";
        }
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String sUserEmail, @RequestParam String sUserPassword, Model model, HttpServletRequest request) {
        try {
            // 아이디에 따라서 회원정보 불러오기
            UserDto userDto = userService.signIn(sUserEmail);
            
            // 로그인 성공 시 세션에 값 넣기 위해서 세션 불러오기
            HttpSession httpSession = request.getSession();

            // 비밀번호 맞는지 확인하기 위해서 Spring security BCryptPasswordEncoder 사용
            BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
            if (PasswordEncoder.matches(sUserPassword,userDto.getSUserPassword())) {
                // 일치하면 세션에 값 저장
                httpSession.setAttribute("sUserEmail", userDto.getSUserEmail());
                httpSession.setAttribute("sUserNickName", userDto.getSUserNickName());
                httpSession.setAttribute("sUserAuth", userDto.getSUserAuth());
            }
            else {
                model.addAttribute("errMsg", "아이디 혹은 비밀번호가 일치하지 않습니다.");
                return "/views/signinform";
            }
        } catch (Exception e) {
            // 에러 콘솔 확인용
            System.out.println("Exception 발생 : " + e);
            model.addAttribute("exception", e.getMessage());
            // 로그인 페이지로 다시 이동
            return "/views/signinform";
        }

        return "redirect:/";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        httpSession.invalidate();

        return "redirect:/";
    }



}
