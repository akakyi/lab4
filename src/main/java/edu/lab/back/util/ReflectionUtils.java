package edu.lab.back.util;

import lombok.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {

    private static final String GETTER_PREFIX = "get";

    public static Method getGetterByField(@NonNull final Class clazz, @NonNull final Field field) {
        final String fieldName = field.getName();
        final char firstNameChar = fieldName.charAt(0);
        final char upperFirstChar = Character.toUpperCase(firstNameChar);
        final String getterName = GETTER_PREFIX + upperFirstChar + fieldName.substring(1);

        try {
            final Method method = clazz.getMethod(getterName);
            return method;
        } catch (NoSuchMethodException e) {
            return null;
        }

    }

}
