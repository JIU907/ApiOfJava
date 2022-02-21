package com.jiu907.api.springboot.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author LeiLiMin
 * @Description: Aop的测试样例
 * @date: 2022/2/21
 */
@Component
@Aspect
public class DemoAopConfig {
    /**
     * 切点表示：
     * 访问修饰符 类的限定名.方法名(形参类型..)
     * *: 表示通配符
     *
     * execution: 表示拦截方法
     */
    @Pointcut("execution(* com.jiu907.api.springboot.config.aop.*.*(..))")
    private void pt() {
    }

    /**
     * {@link Around}: 表示什么类型的通知,里面填入切点
     */
    @Around("pt()")
    public Object around1(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around1");
        return pjp.proceed();
    }
    /**
     * 这里稍微提一下SpringAop的后半段流程
     * 1.在doCreateBean时，对象会经过初始化，属性赋值，实例化三个阶段
     * 2.aop发生在最后一个阶段，在实例化时会调用一些列的BPP方法，
     *   aop相关的就是{@link org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#postProcessAfterInitialization(Object, String)}
     * 3.之后会去寻找所有的AspectJPoint
     * 4.会List<AspectJPoint> 加入一个ExposeInvocationInterceptor.ADVISOR -> 这个类是整个AopAspectJPoint集合的入口，也是轴承
     *   -> 后期的方法调用都是以该类作为入口的
     * 5.然后会进行排序规则依据{@link org.springframework.core.annotation.AnnotationAwareOrderComparator}
     *   -> 可以通过实现 Ordered 接口，来为aspectJPoint进行排序
     *   -> TODO: 后续测试
     */
}
