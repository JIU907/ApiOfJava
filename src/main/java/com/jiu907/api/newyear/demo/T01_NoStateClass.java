package com.jiu907.api.newyear.demo;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author LeiLiMin
 * @Description: 无状态对象
 * @date: 2022/2/6
 */
public class T01_NoStateClass {
    /**
     * 无状态对象--
     *      不包含任何域
     *      不包含其他类中域的引用
     *      计算过程中的临时状态都保存在线程栈中的局部变量表
     * @param bound
     * @return
     */
    public static Integer getNum(Integer bound){
        int random = ThreadLocalRandom.current().nextInt(bound);
        return random;
    }
}
