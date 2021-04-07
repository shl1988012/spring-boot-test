package com.spring.test.util;

import com.spring.test.annotation.demo2.DictToCache;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BeanUtils {
    private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    public static <T> List<T> code2MC(List<T> list) {
        Iterator var1 = list.iterator();

        while (var1.hasNext()) {
            T t = (T) var1.next();
            code2MC(t);
        }

        return list;
    }

    public static <T> T code2MC(T t) {
        if (t == null) {
            return t;
        } else {
            try {
                Class<?> objectClass = t.getClass();
                Field[] at = objectClass.getDeclaredFields();
                Field[] var3 = at;
                for (int i = 0; i < at.length; ++i) {
                    Field fd = at[i];
                    if (fd.isAnnotationPresent(DictToCache.class)) {
                        DictToCache d = fd.getAnnotation(DictToCache.class);
                        fd.setAccessible(true);
                        String code = getDictCode(objectClass, d.fieldName(), t);
//                        fd.set(t, ZdUtils.getDictNames(code, d.dataType()));
                    }
                }
            } catch (Exception var10) {
                logger.error(var10.getMessage(), var10);
            }

            return t;
        }
    }

    private static String getDictCode(Class<?> objectClass, String fieldName, Object o) throws NoSuchFieldException, IllegalAccessException {
        Field codeField = getField(objectClass, fieldName);
        String code = "";
        if (codeField != null) {
            codeField.setAccessible(true);
            Object temp = codeField.get(o);
            if (temp != null) {
                code = temp.toString();
            }
        }

        return code;
    }

    private static Field getField(Class<?> objectClass, String fieldName) throws NoSuchFieldException {
        Field codeField = null;
        try {
            codeField = objectClass.getDeclaredField(fieldName);
        } catch (Exception var7) {
            Class superClass = objectClass.getSuperclass();
            while (superClass != null) {
                try {
                    codeField = superClass.getDeclaredField(fieldName);
                    if (codeField != null) {
                        break;
                    }
                } catch (Exception var6) {
                    superClass = superClass.getSuperclass();
                }
            }
        }
        return codeField;
    }


    public static <T> void mergeObject(T origin, T destination) {
        if (origin == null || destination == null)
            return;
        if (!origin.getClass().equals(destination.getClass()))
            return;

        Field[] fields = origin.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                Object value = fields[i].get(origin);
                if (null != value) {
                    fields[i].set(destination, value);
                }
                fields[i].setAccessible(false);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 从对象中取出值为null的属性名集合
     * @Date 17:45 2021/1/12
     * @Author pengye2
     **/
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return (String[]) emptyNames.toArray(result);
    }
}
