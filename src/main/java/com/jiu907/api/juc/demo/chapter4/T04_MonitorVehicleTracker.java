package com.jiu907.api.juc.demo.chapter4;

import lombok.Data;

import java.util.*;

/**
 * @Author LeiLiMin
 * @Description: 实例封闭: 车辆追踪
 * @date: 2022/2/19
 */
public class T04_MonitorVehicleTracker {
    /**
     * 即使{@link MutablePoint} 不是线程安全的，但是T04_MonitorVehicleTracker是线程安全的
     *
     * 1.locations保存的Map对象和可变对象MutablePoint从未发布(发布出去的是拷贝出的新对象，而不是Map内的对象)
     * 2.每个方法用Synchronized来确保并发访问下的数据一致性
     * 3.外部获取到的MutablePoint对象是与{@link T04_MonitorVehicleTracker#locations}内一致的相同对象，而不是同一个对象！！！
     */
    private final Map<String, MutablePoint> locations;

    public T04_MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(this.locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint mutablePoint = locations.get(id);
        return Objects.isNull(mutablePoint) ? null : new MutablePoint(mutablePoint);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint mutablePoint = this.locations.get(id);
        Optional.of(mutablePoint);
        mutablePoint.setX(x);
        mutablePoint.setY(y);
    }

    /**
     * 通过以下步骤确认实例安全
     * 1.重新生产一个不可修改元素的Map
     * 2.重现创建元素对象 -> 确保不会由于并发影响到{@link T04_MonitorVehicleTracker#locations}的元素
     *
     * @param sourceLocations
     * @return
     */
    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> sourceLocations) {
        Map<String, MutablePoint> copyLocations = new HashMap<>();
        for (String id : sourceLocations.keySet()) {
            MutablePoint mutablePoint = sourceLocations.get(id);
            copyLocations.put(id, new MutablePoint(mutablePoint));
        }
        return Collections.unmodifiableMap(copyLocations);
    }
}

/**
 * 车辆坐标类
 */
@Data
class MutablePoint {
    /**
     * 车辆编号
     */
    private String id;

    /**
     * x坐标
     */
    private int x;

    /**
     * y坐标
     */
    private int y;

    public MutablePoint(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public MutablePoint(MutablePoint p) {
        this.id = p.getId();
        this.x = p.getX();
        this.y = p.getX();
    }
}