package com.jiu907.api.jdk8;

import com.jiu907.api.jdk8.stream.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/2/18
 */
public class T03_Stream {
    public static void main(String[] args) {

    }

    /**
     * 对每一个元素进行筛选操作
     */
    public static void invokeMap(){
        List<User> users = User.getUsers();
        List<Integer> ageList = users.stream().map(e -> e.getAge()).collect(Collectors.toList());
        System.out.println(ageList);
    }

    /**
     * 只能是过滤，决定的是是否留下，而不能改变流中元素的类型
     */
    public static void invokeFilter(){
        List<User> users = User.getUsers();
        // 将filter中返回结构为ture的元素保留下来，过滤为false的元素
        List<User> males = users.stream().filter(e -> e.getSex() == true).collect(Collectors.toList());

        /**
         * User(age=1, sex=true, name=Lewis),
         * User(age=3, sex=true, name=Mike)
         */
        System.out.println(males);
    }

    /**
     * 对集合中的元素进行分组
     */
    public static void invokeGroup() {
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

    /**
     * 将集合中的每个元素通过分隔符拼接起来
     */
    public static void invokeJoin(){
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


    /**
     * 对元素进行操作
     */
    public static void calculateSumOfInt(){
        List<Integer> weight = new ArrayList<>();
        weight.add(1);
        weight.add(3);
        weight.add(5);
        weight.add(7);

        int weightAccMax = weight.stream().mapToInt(Integer::intValue).sum();
    }

}
