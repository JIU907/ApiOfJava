package com.jiu907.api.verify.bitset;

/**
 * @Author LeiLiMin
 * @Description: BitSet
 * @date: 2022/2/15
 */
public class BitSet {
    /**
     * 数组
     */
    private long[] a;

    /**
     * 数组容量
     */
    private int size;

    /**
     * 初始化数组
     * @param cap 容量
     */
    public BitSet(int cap) {
        size = (int) Math.ceil((double) cap / BitMap.BITS_PER_WORD);
        a = new long[size];
    }

    public void set(int i) {
        checkArg(i);
    }

    public void clear(int i) {
        checkArg(i);
        BitMap.clear(a, i);
    }

    public boolean exist(int i) {
        checkArg(i);
        long m = BitMap.test(a, i);
        return m != 0;
    }

    private void checkArg(int i) {
        if (i / BitMap.BITS_PER_WORD > size) {
            throw new IllegalArgumentException("i过大");
        }
    }

    public static void main(String[] args) {
        BitSet bitSet = new BitSet(10);


    }
}

class BitMap {
    // long 占64bit
    public static final int BITS_PER_WORD = 64;
    // 64 = 2^6
    private static final int SHIFT = 6;
    // 64-1 = 0b111111
    private static final int MASK = 0x3f;

    /**
     * 相当于一个数组元素
     * @param a
     * @param i
     */
    public static void set(long[] a, int i) {
        a[i >> SHIFT] |= 1 << (i & MASK);
    }

    public static void clear(long[] a, int i) {
        a[i >> SHIFT] &= ~(1 << (i & MASK));
    }

    public static long test(long[] a, int i) {
        long m = a[i >> SHIFT];
        long n = (1 << (i & MASK));
        return m & n;
    }
}
