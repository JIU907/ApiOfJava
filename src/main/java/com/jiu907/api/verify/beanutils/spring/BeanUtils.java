package com.jiu907.api.verify.beanutils.spring;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author LeiLiMin
 * @Description: Spring Bean Utils
 * @date: 2022/1/17
 */
public class BeanUtils {
    public static void main(String[] args) {
        Source lei = new Source(1, "lei");
        Target target = new Target();
        org.springframework.beans.BeanUtils.copyProperties(lei,target);
        System.out.println(target);

        // 设计一个beanUtils去转换
        Class<Source> sourceClass = Source.class;
        // sourceClass.getdecl
    }
}

@Data
@AllArgsConstructor
class Source{
    private int age;
    private String name;
}
@Data
class Target{
    private int age;
}
