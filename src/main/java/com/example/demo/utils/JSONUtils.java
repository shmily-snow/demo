package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * json工具类
 * 
 * @projectName:comm-base
 * @author:lianss
 * @date:2019年5月8日 上午10:59:19
 * @version 1.0
 */
public class JSONUtils {
    /**
     * 对象转换成json
     * 
     * @param obj
     * @return
     * @author:lianss
     * @createTime:2017年9月19日 下午4:59:16
     */
    public static String toJsonString(Object obj) {
        return JSONObject.toJSONString(obj);
    }

    /**
     * json转换成对象
     * 
     * @param jsonText
     * @param classzz
     * @return
     * @author:lianss
     * @createTime:2017年9月19日 下午4:59:22
     */
    public static <T> T parseObject(String jsonText, Class<T> classzz) {
        return JSONObject.parseObject(jsonText, classzz);
    }

    /**
     * json转换成对象
     * 
     * @param jsonObj
     * @param classzz
     * @return
     * @author:lianss
     * @createTime:2017年9月19日 下午4:59:28
     */
    public static <T> T toJavaObject(JSONObject jsonObj, Class<T> classzz) {
        return JSONObject.toJavaObject(jsonObj, classzz);
    }

    /**
     * json转换成对象
     * 
     * @param obj
     * @param classzz
     * @return
     * @author:lianss
     * @createTime:2017年9月19日 下午4:59:37
     */
    public static <T> T toJavaObject(Object obj, Class<T> classzz) {
        if (null != obj) {
            return JSONObject.toJavaObject((JSONObject)obj, classzz);
        } else {
            return null;
        }
    }

    /**
     * 通过泛型转换json object
     * 
     * @param jsonObj
     * @param type
     * @return
     * @author:lianss
     * @createTime:2017年9月19日 下午4:59:43
     */
    public static <T> T toJavaObject(JSONObject jsonObj, TypeReference<T> type) {
        if (null != jsonObj) {
            return JSONObject.parseObject(jsonObj.toJSONString(), type);
        } else {
            return null;
        }
    }

    /**
     * 把Json串转换成JSONObject对象
     * 
     * @param josnContent
     * @return
     * @author:lianss
     * @createTime:2017年9月19日 下午4:59:52
     */
    public static JSONObject parseObject(String josnContent) {
        return JSONObject.parseObject(josnContent);
    }
}
