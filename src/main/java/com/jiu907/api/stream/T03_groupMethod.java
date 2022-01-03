package com.jiu907.api.stream;

import com.jiu907.api.stream.model.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LeiLiMin
 * @Date 2021/11/12 17:38
 * @Description 测试 分组
 */
public class T03_groupMethod {
    public static void main(String[] args) {
        List<User> users = User.getUsers();

        // 按照名称分组
        Map<String, List<User>> name = users.stream().collect(Collectors.groupingBy(User::getName));

        // 按照性别进行分区，分区的Key都为Boolean
        Map<Boolean, List<User>> malePartition = users.stream().collect(Collectors.partitioningBy(e -> e.getSex() == true));

        // 先按照性别分组，再按照年龄分组
        Map<Boolean, Map<Integer, List<User>>> maleAndAge = users.stream().collect(Collectors.groupingBy(User::getSex, Collectors.groupingBy(User::getAge)));

        // result

        /**
         * {Mike=[User(age=3, sex=true, name=Mike)],
         * Lewis=[User(age=1, sex=true, name=Lewis)],
         * Lucy=[User(age=2, sex=false, name=Lucy)],
         * Lily=[User(age=4, sex=false, name=Lily)]}
         */
        System.out.println(name);

        /**
         * 不满足条件:false=[User(age=2, sex=false, name=Lucy), User(age=4, sex=false, name=Lily)]
         *  满足条件:true=[User(age=1, sex=true, name=Lewis), User(age=3, sex=true, name=Mike)]}
         */
        System.out.println(malePartition);

        /**
         * {
         *  false={2=[User(age=2, sex=false, name=Lucy)],
         *         4=[User(age=4, sex=false, name=Lily)]},
         *  true={1=[User(age=1, sex=true, name=Lewis)],
         *        3=[User(age=3, sex=true, name=Mike)]}}
         */
        System.out.println(maleAndAge);
    }
}
