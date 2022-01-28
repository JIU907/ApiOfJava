package com.jiu907.api.springboot.utils.randomutil;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @Author LeiLiMin
 * @Description: getRandomFromWeigh
 * @date: 2021/12/16
 */
@Data
@NoArgsConstructor
public class RandomUtil {
    public static void main(String[] args) {
        List<Integer> value=new ArrayList<>();
        value.add(1);
        value.add(2);
        value.add(3);
        List<Integer> weight=new ArrayList<>();
        weight.add(1);
        weight.add(2);
        weight.add(3);

        int totalNum=99999999;
        int result1=0;
        int result2=0;
        int result3=0;
        for (int i = 0; i < totalNum; i++) {
            Integer randomSchemeByWeight = getRandomSchemeByWeight(value, weight);
            switch (randomSchemeByWeight){
                case 1:
                    result1++;
                    break;
                case 2:
                    result2++;
                    break;
                case 3:
                    result3++;
                    break;
                default:
                    throw new RuntimeException("Calculate Error");
            }
        }
        System.out.println("result1:"+result1+"\tAvg:"+(float)result1/(float)totalNum);
        System.out.println("result2:"+result2+"\tAvg:"+(float)result2/(float)totalNum);
        System.out.println("result3:"+result3+"\tAvg:"+(float)result3/(float)totalNum);



    }
    /**
     * why?
     * 1.根据上诉权重可得：1，2，2，3，3，3
     * 2.根据以下逻辑
     *      *：random出的值会很随机的遍布在上诉列表的某一点上
     *      *：而在每次循环不匹配时的扣除当次的值，这就将整个列表抽象成了一个域图
     */
    public static Integer getRandomSchemeByWeight(List<Integer> values,List<Integer> weight){
        // 权重求和
        int weightAccMax = weight.stream().mapToInt(Integer::intValue).sum();
        // 求出random
        int randomWeight = ThreadLocalRandom.current().nextInt(0, weightAccMax);
        for (int i = 0; i < weight.size(); i++) {
            if (randomWeight < weight.get(i)) {
                return values.get(i);
            }
            randomWeight -= weight.get(i);
        }
        return values.get(0);
    }

    /**
     * 通过已经使用的元素在全量数组上快速获取随机的可用元素
     * why?
     * 1.主要是为了防止，当可用元素存量极少时，发送高概率的空转
     */
    public static <T> T test(T[] allData, List<T> used){
        Set<T> currentIndex = used.stream().collect(Collectors.toSet());
        List<T> usableIndex = Arrays.stream(allData).filter(e -> !currentIndex.contains(e)).collect(Collectors.toList());
        int size = usableIndex.size();
        int chooseIndex = ThreadLocalRandom.current().nextInt(size);
        return usableIndex.get(chooseIndex);
    }
}
