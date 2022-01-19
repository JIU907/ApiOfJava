package com.jiu907.api.verify.bi;

import com.google.gson.Gson;
import com.jiu907.api.verify.bi.utils.DefaultThreadFactory;
import com.jiu907.api.verify.bi.utils.TraceUtil;
import com.sensorsdata.analytics.javasdk.ISensorsAnalytics;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.bean.EventRecord;
import com.sensorsdata.analytics.javasdk.consumer.ConcurrentLoggingConsumer;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author LeiLiMin
 * @Description: 神策埋点服务
 * @date: 2022/1/17
 */
@Slf4j
public class SensorService {
    // 日志文件路径
    private static final String LOG_FILE_PATH = "/Users/leilimin/sensors_log/";

    // 生产数据的顶级接口对象
    public static ISensorsAnalytics sa;

    // 线程服务
    private static ScheduledExecutorService scheduledExecutorService;

    // 系统环境：开发环境
    private static String ENV_DEV = "DEV";

    // 系统环境：生产环境
    private static String ENV_PRODUCT = "PRODUCT";

    private static Gson gson = new Gson();


    static {
        scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new DefaultThreadFactory("SensorsTask"));
    }

    public SensorService() {
        String env = System.getenv("ENV");
        if (ENV_DEV.equals(env)) {
            /**
             * 开发环境：该模式会逐条校验数据并在校验失败时抛出异常
             * serverUrl: 数据接收地址数据是否入库，
             * writeData: true:表示数据入库；false:表示数据不入库（只展示不保存）
             */
            this.sa = new SensorsAnalytics(new ConcurrentLoggingConsumer(LOG_FILE_PATH));
        }
        if (ENV_PRODUCT.equals(env)) {
            /**
             * 生产环境: 使用 ConcurrentLoggingConsumer导入数据。
             *          支持多个进程写同一个目录（目录不能是 nas、nfs 类文件系统）
             */
            this.sa = new SensorsAnalytics(new ConcurrentLoggingConsumer(LOG_FILE_PATH));
            // 每隔 5 分钟定时 flush 一下，解决某个服务埋点量少，flush 不及时的问题
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                this.sa.flush();
            }, 1L, 5L, TimeUnit.MINUTES);
        }

        // 为JVM增加一个Hook，当Jvm退出时，让consumer及时flush
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                Optional.ofNullable(sa).ifPresent(sa -> sa.shutdown());
            }
        });
    }


    /**
     * 记录一个没有任何属性的事件
     *
     * @param distinctId 用户ID
     * @param eventName  事件名称
     * @param isLoginId  为true时distinctId是一个userId  false时是设备ID
     */
    public void track(String distinctId, String eventName, boolean isLoginId) {
        try {
            sa.track(distinctId, isLoginId, eventName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 记录一个拥有一个或多个属性的事件。属性取值可接受类型为{@link Number}, {@link String}, {@link Date}和
     * {@link List}，若属性包含 $time 字段，则它会覆盖事件的默认时间属性，该字段只接受{@link Date}类型
     *
     * @param distinctId 用户ID
     * @param eventName  事件名称
     * @param properties 事件的属性
     * @param isLoginId  为true时distinctId是一个userId  false时是设备ID
     */
    public void track(String distinctId, String eventName, Map<String, Object> properties, boolean isLoginId) {
        properties = this.addTraceId(eventName, properties);
        try {
            // 1.设置distinctId，eventName，isLoginId
            EventRecord build = EventRecord
                    .builder()
                    .setDistinctId(distinctId)
                    .isLoginId(isLoginId)
                    .setEventName(eventName)
                    .addProperties(properties).build();
            // 2.记录用户浏览商品事件
            sa.track(build);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    /**
     * 存入TraceId
     * @param properties
     * @return
     */
    private Map<String, Object> addTraceId(String eventName, Map<String, Object> properties) {
        String traceId = TraceUtil.readTraceId();
        if (StringUtils.isBlank(traceId)) {
            traceId = TraceUtil.createTraceId();
        }
        properties.put(TraceConstants.TRACE_ID_SENSOR_KEY, traceId);
        return properties;
    }

    /**
     * 记录用户注册事件
     * 绑定distinctId与UserId，用于后期分析
     *
     * @param distinctId       新的用户ID
     * @param originDistinctId 旧的用户ID
     */
    public void trackSignUp(String distinctId, String originDistinctId) {
        try {
            sa.trackSignUp(distinctId, originDistinctId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) throws InvalidArgumentException {
        SensorService sensorService = new SensorService();
        // sensorService.trackSignUp("2", "1");
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        map.put("age", list);
        map.put("name", "lewis");
        map.put("int", 1);
        map.put("float", 1.1f);
        map.put("double", 2.2D);
        map.put("short", (short) 1);
        map.put("byte", (byte) 1);
        // map.put("char",(char)1); 行不通

        sensorService.track("222", "new_Method", map, false);
    }
}
