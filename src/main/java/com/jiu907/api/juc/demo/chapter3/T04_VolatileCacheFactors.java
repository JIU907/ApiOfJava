package com.jiu907.api.juc.demo.chapter3;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @Author LeiLiMin
 * @Description: 不可变的类
 * @date: 2022/2/9
 */
public class T04_VolatileCacheFactors {
    /**
     * 1.cache被volatile修饰 -> 它的每次变化都可以被其他线程及时感知到
     * 2.ImmutableClass是一个不可变的类 -> 其他线程修改不了本线程的对象
     */
    private volatile ImmutableClass cache = new ImmutableClass(null, null);

    /**
     * 这可以使得cache#lastNumber和cache#lastFactors可以同时得到更新
     * 1.之前是将lastNumber和lastFactors存储在2个变量中，所以就导致了非同步下的安全问题
     */
    public BigInteger[] updateCache(BigInteger i) {
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            cache = new ImmutableClass(i, new BigInteger[]{});
        }
        return cache.getFactors(i);
    }
}

/**
 * 重申一下这个类的作用，为了获取最近更新的缓存
 */
class ImmutableClass {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public ImmutableClass(BigInteger i, BigInteger[] factors) {
        this.lastNumber = i;
        this.lastFactors = factors;
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        }
        return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}
