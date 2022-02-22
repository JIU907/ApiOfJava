package com.jiu907.api.juc.demo.chapter4;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author LeiLiMin
 * @Description: 将线程的安全性委托给多个状态变量
 * @date: 2022/2/22
 */
public class T05_VisualComponent {
    /**
     * 1.keyListeners与mouseListeners被final修饰 -> 所以CopyOnWriteArrayList的引用不可被改变
     * 2.现有方法并没有导致CopyOnWriteArrayList对象发布逸出
     * 3.CopyOnWriteArrayList是一个线程安全的容器
     * 4.所以T05_VisualComponent是一个线程安全的类
     * -> 这也建立在这2个CopyOnWriteArrayList是没有联系的基础上
     * <p>
     * 5.T05_VisualComponent的状态由2个CopyOnWriteArrayList来决定
     * 这2个CopyOnWriteArrayList对象是线程安全的，所以T05_VisualComponent也是线程安全的
     */
    private final List<KeyListeners> keyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListeners> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListeners(KeyListeners keyListener) {
        keyListeners.add(keyListener);
    }

    public void addMuseListeners(MouseListeners mouseListener) {
        mouseListeners.add(mouseListener);
    }

    public void removeKeyListeners(KeyListeners keyListener) {
        keyListeners.remove(keyListener);
    }

    public void removeMuseListeners(MouseListeners mouseListener) {
        mouseListeners.remove(mouseListener);
    }

}

class KeyListeners {

}

class MouseListeners {

}
