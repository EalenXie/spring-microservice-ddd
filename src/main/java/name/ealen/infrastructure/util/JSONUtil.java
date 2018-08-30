package name.ealen.infrastructure.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Created by EalenXie on 2018/8/16 9:59.
 * (枚举单例常用工具类)JSON字符串与Java对象互转
 */
public enum JSONUtil {

    getJsonUtil;    //枚举单例工具类

    /**
     * @param json  传入json字符串
     * @param clazz 传入Java泛型class
     * @return 将JSON字符串转换成Java对象
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        return JSONObject.toJavaObject(JSONObject.parseObject(json), clazz);
    }

    /**
     * @param jsonArray 数组型JSON字符串
     * @param clazz     传入Java泛型class
     * @return 将数组型JSON字符串转换成Java的List对象
     */
    public static <T> ArrayList<T> jsonToListObject(String jsonArray, Class<T> clazz) {
        return (ArrayList<T>) JSONArray.parseArray(jsonArray).toJavaList(clazz);
    }

}
