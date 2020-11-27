package cn.ycc.api.admin.commons.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

/**
 * 淘淘商城自定义响应结构
 */
public class JsonUtils implements Serializable{

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			throw new RuntimeException("转换json异常");
		}
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
			throw new RuntimeException("转换json异常");
        }
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			throw new RuntimeException("转换json异常");
		}
    	
    }
	/**
	 * 将json字符串转换为Map
	 * @param jsonData 被转换字符串
	 * @param keyType  返回值map的key类型
	 * @param valType  map的 value类型
	 * @return
	 */
	public static <T,V>Map<T,V> jsonToMap(String jsonData, Class<T> keyType, Class<V> valType) {
		JavaType javaType = MAPPER.getTypeFactory().constructMapType(Map.class,keyType, valType);
		try {
			Map<T,V> map= MAPPER.readValue(jsonData,javaType);
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("转换json异常");
		}
	}
    
}
