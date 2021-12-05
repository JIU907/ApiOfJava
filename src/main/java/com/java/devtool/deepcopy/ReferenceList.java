package com.java.devtool.deepcopy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Lewis
 * @Date 2021/12/3 23:24
 * @Description 引用类型序列化
 */
public class ReferenceList {
	public static void main(String[] args){
		List<List<Person>> test = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			List<Person> subList = new ArrayList<>();
			for (int j = 0; j < 10000; j++) {
				Person person = new Person(i + ":" + j, j);
				subList.add(person);
			}
			test.add(subList);
		}
		long begin = System.currentTimeMillis();
		String val = new Gson().toJson(test);
		Type type = new TypeToken<List<List<Person>>>() {
		}.getType();
		Object o = new Gson().fromJson(val, type);
		long end = System.currentTimeMillis();
		System.out.println(end - begin);

		begin = System.currentTimeMillis();
		Object o1 = deepCopy(test);
		end = System.currentTimeMillis();
		System.out.println(end - begin);

		System.out.println(o1.toString().equals(o.toString()));

	}

	/**
	 * 实现里层深拷贝
	 * 需求：高性能
	 */
	public static Object deepCopy(Collection c) {
		Object[] a = c.toArray();
		if (a.length != 0 && (c.getClass() == ArrayList.class)) {
			ArrayList<Object> result = new ArrayList<>();
			Object processor = processor(result, a, null, null);
			return processor;
		}
		return null;
	}

	public static Object processor(ArrayList<Object> result, Object[] a, Class<?> cacheClass, Map<String, Method> cacheMethodMap) {
		for (int i = 0; i < a.length; i++) {
			// 如果数组中的元素出现 集合类型，就递归处理
			if (a[i].getClass() == ArrayList.class) {
				ArrayList<Object> subElement = new ArrayList<>();
				result.add(subElement);
				processor(subElement, ((Collection) a[i]).toArray(), cacheClass, cacheMethodMap);
			} else {
				if (cacheClass == null)
					cacheClass = a[i].getClass();
				if (cacheMethodMap == null)
					cacheMethodMap = Arrays.stream(cacheClass.getDeclaredMethods()).collect(Collectors.toMap(Method::getName, e -> e));
				Object finalObject = createElement(cacheClass, a[i], cacheMethodMap);
				result.add(finalObject);
			}
		}
		return result;
	}

	/**
	 * 通过反射创建元素
	 */
	public static Object createElement(Class type, Object source, Map<String, Method> methodMap) {
		try {
			Object finalObject = type.newInstance();
			Field[] declaredFields = type.getDeclaredFields();
			for (Field declaredField : declaredFields) {
				char[] filedName = declaredField.getName().toCharArray();
				filedName[0] = toUpperCase(filedName[0]);

				Method setMethod = methodMap.get("set" + String.valueOf(filedName));
				Method getMethod = methodMap.get("get" + String.valueOf(filedName));

				setMethod.setAccessible(true);
				getMethod.setAccessible(true);

				setMethod.invoke(finalObject, getMethod.invoke(source));
			}
			return finalObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 字符转大写
	 */
	public static char toUpperCase(char c) {
		if (97 <= c && c <= 122) {
			c ^= 32;
		}
		return c;
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
	String name;
	Integer age;
}