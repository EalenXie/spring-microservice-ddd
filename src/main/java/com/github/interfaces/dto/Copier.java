package com.github.interfaces.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @author EalenXie create on 2021/2/19 14:58
 * 实现此接口让对象具有基本属性拷贝的能力
 */
public interface Copier {

    /**
     * 传入对象,对其进行属性拷贝
     *
     * @param target 拷贝目标对象
     */
    default <T> void copy(T target) {
        BeanUtils.copyProperties(this, target);
    }

    /**
     * 传入对象类型, 进行属性拷贝并且得到其对象
     *
     * @param type 对象类型 Class对象
     * @return 对象
     */
    default <T> T copy(Class<T> type) {
        T target = BeanUtils.instantiateClass(type);
        copy(target);
        return target;
    }

    /**
     * 不为null的属性值才会拷贝
     *
     * @param target 拷贝目标对象
     */
    default <T> void copyNotNull(T target) {
        BeanWrapper wrapper = new BeanWrapperImpl(this);
        PropertyDescriptor[] pds = wrapper.getPropertyDescriptors();
        Set<String> properties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : pds) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = wrapper.getPropertyValue(propertyName);
            if (propertyValue != null) {
                if (propertyValue instanceof String && !StringUtils.hasText((String) propertyValue)) {
                    properties.add(propertyName);
                }
            } else {
                properties.add(propertyName);
            }
        }
        BeanUtils.copyProperties(this, target, properties.toArray(new String[0]));
        properties.clear();
    }
}
