package com.java.devtool.deepcopy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author Lewis
 * @Date 2021/12/3 23:24
 * @Description 基本数据类型的深拷贝
 */
public class BasicList {
	public static void main(String[] args) {
		List<List<Long>> test = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			List<Long> subList = new ArrayList<>();
			for (int j = 0; j < 1300; j++) {
				subList.add(i + j + 1L);
			}
			test.add(subList);
		}

		// Gson
		long begin = System.currentTimeMillis();
		String val = new Gson().toJson(test);
		Type type = new TypeToken<List<List<Integer>>>() {
		}.getType();
		Object o = new Gson().fromJson(val, type);
		long end = System.currentTimeMillis();
		System.out.println(end - begin);

		// Reflect
		begin = System.currentTimeMillis();
		Object o1 = deepCopy(test);
		end = System.currentTimeMillis();
		System.out.println(end - begin);

		// Final result
		System.out.println(o1.toString().equals(o.toString()));

	}

	/**
	 * 获取数据类型
	 */
	public static Class getClass(Collection c) {
		Object[] a = c.toArray();
		while (true) {
			if (a.length != 0 && (a[0] instanceof Collection)) {
				a = ((Collection) a[0]).toArray();
				continue;
			}
			return a[0].getClass();
		}
	}

	public static void basicTypeCheck(Class T) {
		if (!(T == Short.class ||
				T == Integer.class ||
				T == Byte.class ||
				T == Long.class ||
				T == Double.class)) {
			throw new RuntimeException("[" + T + "] is not a basic type!");
		}
	}

	public static Constructor getBasicConstructor(Class type) {
		try {
			Constructor constructor = null;
			if (type == Byte.class) {
				constructor = type.getConstructor(byte.class);
			}
			if (type == Short.class) {
				constructor = type.getConstructor(short.class);
			}
			if (type == Integer.class) {
				constructor = type.getConstructor(int.class);
			}
			if (type == Long.class) {
				constructor = type.getConstructor(long.class);
			}
			if (type == Double.class) {
				constructor = type.getConstructor(double.class);
			}
			return constructor;
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("NoSuchMethodException");
		}
	}

	/**
	 * 实现里层深拷贝
	 * 需求：高性能
	 */
	public static Object deepCopy(Collection c) {
		Object[] a = c.toArray();
		if (a.length != 0) {
			// 获取最内层 类型
			Class type = getClass(c);
			// 是否位基本数据类型包装类
			basicTypeCheck(type);
			// 获取对应的包装类
			Constructor basicConstructor = getBasicConstructor(type);
			// 深拷贝开始
			return processor(new ArrayList<>(), a, basicConstructor);
		}
		return c == null ? null : new ArrayList<>();
	}

	public static Object processor(ArrayList<Object> result, Object[] a, Constructor constructor) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] instanceof Collection) {
				ArrayList<Object> subElement = new ArrayList<>();
				result.add(subElement);
				processor(subElement, ((Collection) a[i]).toArray(), constructor);
			} else {
				try {
					result.add(constructor.newInstance(a[i]));
				} catch (Exception e) {
					throw new RuntimeException("InstanceException");
				}
			}
		}
		return result;
	}
}

