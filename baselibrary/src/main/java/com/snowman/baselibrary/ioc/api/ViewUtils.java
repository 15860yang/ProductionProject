package com.snowman.baselibrary.ioc.api;

import android.app.Activity;
import android.view.View;

import com.snowman.baselibrary.ioc.annotation.OnClick;
import com.snowman.baselibrary.ioc.annotation.ViewById;
import com.snowman.baselibrary.utils.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils {

    public static void inject(Activity activity) {
        inject(new ViewFinder(activity), activity);
    }

    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    public static void inject(View view, Object object) {
        inject(new ViewFinder(view), object);
    }

    /**
     * @param finder View的辅助类
     * @param object 反射执行的所在类
     */
    private static void inject(ViewFinder finder, Object object) {
        injectField(finder, object);
        injectEvent(finder, object);
    }

    /**
     * 注入属性
     *
     * @param finder
     * @param object
     */
    private static void injectField(ViewFinder finder, Object object) {
        //获取类里面所有的属性
        Field[] allFields = ReflectUtil.getAllField(object.getClass());
        for (Field field : allFields) {
            ViewById annotation = field.getAnnotation(ViewById.class);
            if (annotation != null) {
                int viewId = annotation.value();
                View view = finder.findViewById(viewId);
                try {
                    field.setAccessible(true);
                    field.set(object, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //获取ViewById注解
        //findViewByID找到View
        //动态注入找到的View
    }

    private static void injectEvent(ViewFinder finder, Object object) {
        Method[] allMethods = ReflectUtil.getAllMethod(object.getClass());
        for (Method method : allMethods) {
            OnClick annotation = method.getAnnotation(OnClick.class);
            if (annotation != null) {
                int[] viewIds = annotation.value();
                for (int viewId : viewIds) {
                    View view = finder.findViewById(viewId);
                    view.setOnClickListener(new DeclaredOnClickListener(method, object));
                }
            }
        }
    }

    public static class DeclaredOnClickListener implements View.OnClickListener {

        private Method method;
        private Object object;

        DeclaredOnClickListener(Method method, Object object) {
            this.method = method;
            this.object = object;
        }

        @Override
        public void onClick(View v) {
            try {
                Class<?>[] parameterTypes = method.getParameterTypes();
                method.setAccessible(true);
                if (parameterTypes.length != 1) {
                    method.invoke(object);
                } else {
                    method.invoke(object, v);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("the OnClick Annotation should be used a Method with no param or view param!");
            }
        }
    }
}
