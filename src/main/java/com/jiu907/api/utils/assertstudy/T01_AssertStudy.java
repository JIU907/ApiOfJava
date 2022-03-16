package com.jiu907.api.utils.assertstudy;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/3/16
 */
public class T01_AssertStudy {
    public static void main(String[] args) {
        /**
         * assert-断言：
         * 我的理解为是一种比try{}catch{}更加轻量级的控制代码块方式
         * try{}catch{}: 针对的是异常,需要自定义处理
         * assert: 为什么说是轻量级呢？
         *           -: 因为一但判断为false,就会抛出异常[从而终结代码块],而无需特殊处理
         */

        /**
         * 如何使用断言
         * assert expression[return boolean]
         */
        // 1.如果想要代言有效，需要添加VM Options: -ea
        assert false;
        System.out.println(1);
    }
}
