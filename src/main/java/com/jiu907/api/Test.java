package com.jiu907.api;

import com.jiu907.api.jdk8.stream.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/2/15
 */
public class Test {
    public static void main(String[] args) {
        Long[] arrar={1L,2L,3L,4L};
        StringBuffer sb=new StringBuffer();
        for (Long aLong : arrar) {
            sb.append(aLong+",");
        }
        String substring = sb.substring(0, sb.length() - 1);
        System.out.println(substring);

        String collect = Arrays.stream(arrar).map(e -> e.toString()).collect(Collectors.joining(","));
        System.out.println(collect);

    }
}
