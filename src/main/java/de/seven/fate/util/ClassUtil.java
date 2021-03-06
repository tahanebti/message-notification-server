package de.seven.fate.util;

import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@UtilityClass
public final class ClassUtil {

    public static <T> T getGenericType(Class<?> classType, int index) {
        ParameterizedType genericSuperclass = (ParameterizedType) classType.getGenericSuperclass();

        return getGenericType(genericSuperclass, index);
    }

    public static <T> T getGenericType(Type classType, int index) {
        ParameterizedType genericSuperclass = (ParameterizedType) classType;

        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();

        return (T) actualTypeArguments[index];
    }

    public static <T> T newInstance(Class<T> genericType) {

        try {
            return genericType.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create new instance of " + genericType, e);
        }
    }

    public static int getIndexOfParameter(Annotation[][] annotations, Class<?> annotationType) {

        for (int index = 0; index < annotations.length; index++) {
            Annotation[] subAnnotations = annotations[index];
            for (Annotation annotation : subAnnotations) {
                if (annotation.annotationType() == annotationType) {
                    return index;
                }
            }
        }

        return -1;
    }

}
