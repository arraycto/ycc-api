package cn.ycc.api.admin.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.24 20:09
 */
public  abstract  class PropertiesFileUtils {
    private static final Map<String,Map<String,String>> cachePropertiesFileMap = new ConcurrentHashMap<>();

    public static Map<String,String> getFileProperties(String resourceName){
        Map<String, String> stringMap = cachePropertiesFileMap.get(resourceName);

        if(stringMap == null){
            synchronized (resourceName){
                stringMap =  cachePropertiesFileMap.get(resourceName);
                if(stringMap == null){
                    stringMap = readFileProperties(resourceName);
                    cachePropertiesFileMap.put(resourceName,stringMap);
                }
            }
        }
        return stringMap;

    }

    private static Map<String, String> readFileProperties(String resourceName) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(resourceName);
        Enumeration<String> enumeration = resourceBundle.getKeys();

        Map<String, String> map = new HashMap<>();
        while (enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            String value = resourceBundle.getString(key);
            map.put(key,value);
        }
        return map;
    }
}
