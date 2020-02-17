package com.snowman.baselibrary.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil {

    public static Field[] getAllField(Class clazz) {
        return clazz.getDeclaredFields();
    }


    public static Method[] getAllMethod(Class<?> aClass) {
        return aClass.getDeclaredMethods();
    }
}
