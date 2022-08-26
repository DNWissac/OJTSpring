package com.ojt.movie.util;

import java.text.SimpleDateFormat;

public class DateFormat {
    public static void main(String[] args) {
        String exDate1 = "2021/01/01";
        String exDate2 = "20210101";

        System.out.println(checkDate(exDate1));
        System.out.println(checkDate(exDate2));
    }

    public static boolean checkDate(String checkDate) {
        try {
            SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy/MM/dd"); //검증할 날짜 포맷 설정
            dateFormatParser.setLenient(false); //false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
            dateFormatParser.parse(checkDate); //대상 값 포맷에 적용되는지 확인
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
