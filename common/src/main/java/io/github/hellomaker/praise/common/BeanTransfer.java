package io.github.hellomaker.praise.common;


import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * bean转换器
 * @author xianzhikun
 * @date
 **/
public class BeanTransfer {


    /**
     * 功能描述: 列表copy
     * @Param: [source, claz]
     * @Return: java.util.List<T>
     * @Author: xianzhikun
     * @Date: 2023/7/18 14:26
     */
    public static <T> List<T> transferList(List<?> source, Class<T> claz) {
        try {
            List<T> transfer = new ArrayList<>();
            for (Object sourceObject : source) {
                Object toObject = claz.newInstance();
                BeanUtils.copyProperties(sourceObject, toObject);
                transfer.add((T) toObject);
            }
            return transfer;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述: 列表copy
     * @Param: [source, claz]
     * @Return: java.util.List<T>
     * @Author: xianzhikun
     * @Date: 2023/7/18 14:26
     */
    public static <T, S> List<T> transferList(List<S> source, Class<T> targetClaz, BiConsumer<S, T> consumer) {
        try {
            List<T> transfer = new ArrayList<>();
            for (S sourceObject : source) {
                try {
                    Constructor<T> constructor = targetClaz.getConstructor();
                    T instance = constructor.newInstance();
                    BeanUtils.copyProperties(sourceObject, instance);
                    consumer.accept(sourceObject, instance);
                    transfer.add(instance);
                } catch (NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                    Object toObject = targetClaz.newInstance();
                    BeanUtils.copyProperties(sourceObject, toObject);
                    consumer.accept(sourceObject, (T)toObject);
                    transfer.add((T) toObject);
                }
//                BeanUtils.copyProperties(sourceObject, toObject);
            }
            return transfer;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T, S> T transfer(S sourceObject, Class<T> targetClaz, BiConsumer<S, T> consumer) {
        try {
            try {
                Constructor<T> constructor = targetClaz.getConstructor();
                T instance = constructor.newInstance();
                BeanUtils.copyProperties(sourceObject, instance);
                consumer.accept(sourceObject, instance);
                return instance;
            } catch (NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                Object toObject = targetClaz.newInstance();
                BeanUtils.copyProperties(sourceObject, toObject);
                consumer.accept(sourceObject, (T)toObject);
                return (T)toObject;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T, S> T transfer(S sourceObject, Class<T> targetClaz) {
        try {
            try {
                Constructor<T> constructor = targetClaz.getConstructor();
                T instance = constructor.newInstance();
                BeanUtils.copyProperties(sourceObject, instance);
                return instance;
            } catch (NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                Object toObject = targetClaz.newInstance();
                BeanUtils.copyProperties(sourceObject, toObject);
                return (T)toObject;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T>T doToDto(Object source, Class<T> claz) {
        return transfer(source, claz);
    }

    public static <T>T dtoToDo(Object source, Class<T> claz) {
        return transfer(source, claz);
    }
}
