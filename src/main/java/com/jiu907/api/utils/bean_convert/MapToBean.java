package com.jiu907.api.utils.bean_convert;

import com.jiu907.api.jdk8.stream.model.User;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LeiLiMin
 * @Date 2021/11/11 19:40
 * @Description TODO
 */
public class MapToBean {
    public static void main(String[] args) {
        // 创建一个Map，key 和 Filed 对应
        Map<String,Object> map=new HashMap<>();
        map.put("age",1);
        map.put("sex",true);
        map.put("name","Lewis");

        // 创建目标对象
        User user=new User();
        try {
            // 完成MapToBean
            BeanUtils.populate(user,map);
            System.out.println(user);
        } catch (Exception e) {
            System.out.println("wrong");
        }

    }
}
