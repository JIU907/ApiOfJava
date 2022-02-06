package com.jiu907.api.newyear.demo;

/**
 * @Author LeiLiMin
 * @Description: 懒加载中的竞态条件
 * @date: 2022/2/6
 */
public class T02_LazyInitRace {
    private T02_LazyInitRace lazyInitRace;

    /**
     * 竞态条件--
     * 并发的进入到if (lazyInitRace == null)内部
     *
     * @return
     */
    public T02_LazyInitRace getLazyInitRace() {
        if (lazyInitRace == null) {
            lazyInitRace = new T02_LazyInitRace();
        }
        return lazyInitRace;
    }
}
