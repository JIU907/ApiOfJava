package com.jiu907.api.juc.demo;

/**
 * @Author LeiLiMin
 * @Description: 重入锁
 * @date: 2022/2/6
 */
public class T04_Widget {
    public synchronized void doSomething() {
        System.out.println("Super doSomething");
        while (true){

        }
    }

    public static void main(String[] args) {
        LoggingWidget loggingWidget = new LoggingWidget();
        loggingWidget.doSomething();
    }
}

class LoggingWidget extends T04_Widget {
    @Override
    public synchronized void doSomething() {
        System.out.println("sub doSomething");
        super.doSomething();
    }
}
// jstack 命令中关于锁的信息
/**
 * "main" #1 prio=5 os_prio=31 cpu=13757.96ms elapsed=13.91s tid=0x00007f9dcd809000 nid=0x1703 runnable  [0x00007000061f1000]
 *    java.lang.Thread.State: RUNNABLE
 * 	at com.jiu907.api.newyear.demo.T04_Widget.doSomething(T04_Widget.java:11)
 * 	- locked <0x000000070f909550> (a com.jiu907.api.newyear.demo.LoggingWidget)
 * 	at com.jiu907.api.newyear.demo.LoggingWidget.doSomething(T04_Widget.java:26)
 * 	- locked <0x000000070f909550> (a com.jiu907.api.newyear.demo.LoggingWidget)
 * 	at com.jiu907.api.newyear.demo.T04_Widget.main(T04_Widget.java:18)
 *
 *    Locked ownable synchronizers:
 * 	- None
 */

