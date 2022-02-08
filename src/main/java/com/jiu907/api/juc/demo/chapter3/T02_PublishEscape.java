package com.jiu907.api.juc.demo.chapter3;

import com.jiu907.api.jdk8.stream.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author LeiLiMin
 * @Description: 对象的发布与溢出
 * @date: 2022/2/8
 */
public class T02_PublishEscape {
    // 为什么强调溢出：因为任何调用者都可以操作该溢出对象，这就造成了安全问题
    /**
     * 移除1：userSet不仅可以被外部对象访问到也可以被内部方法操作
     */
    public static Set<User> userSet;

    public void initialize() {
        userSet = new HashSet<>();
    }

    /**
     * 溢出2：私有对象被共有方法发布了
     */
    private String[] states = new String[]{"test1", "test2"};

    private String[] getStates() {
        return states;
    }

    /**
     * 溢出3：外部方法(不是私有的，不是final)的方法通过被内部方法发布的对象进行操作
     * 也会操作对象的溢出
     *
     * @return
     */

    public void alien() {
        Object internal = internal();
        // 对 internal 进行一些列操作
    }

    private Object internal() {
        return new Object();
    }

    /**
     * 溢出4：内部类实例发布
     * 调用ThisEscape的实力化方法时，发布了EscapeInvoke对象
     * 但同时也调用了EscapeInvoke#doSomething -> 这里只是举个例子，我们当然也可以实现this对象溢出
     * // TODO：电池不足，明天补充
     */

    public class ThisEscape {
        private int value;

        public ThisEscape() {
            new EscapeInvoke() {
                @Override
                public void registerListener() {
                    doSomething(new Object());
                }
            };
        }


        public void doSomething(Object e) {
            System.out.println(value);
        }
    }

    public abstract class EscapeInvoke {
        public abstract void registerListener();
    }
}
