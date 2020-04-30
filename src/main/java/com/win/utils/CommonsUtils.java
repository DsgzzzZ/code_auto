package com.win.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.win.vo.TableColumnData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName CommonsUtil
 * @Description TODO(工具类)
 * @author huiziqin
 * @Date 2018年1月8日 下午2:32:25
 * @version 1.0.0
 */
public class CommonsUtils {

    /* 根据文件名字和字段名称取出properties文件中的值 */
    public static String getProperty(String propertiesFile, String propertyName) {
        Properties properties = new Properties();
        InputStream inputStream = CommonsUtils.class.getClassLoader().getResourceAsStream(propertiesFile);
        try {
            properties.load(inputStream);
            inputStream.close();
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description (时间戳转化为Date)
     * @param dateMs
     * @return
     * @throws Exception
     */
    public static String dateFormatToDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * @Description (首字母大写 去空格大写)
     * @param tableName
     * @return
     */
    public static String getTableNameToClassName(String tableName) {
        String[] split = tableName.split("_");
        if (split.length > 1) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < split.length; ++i) {
                String tempTableName = split[i].substring(0, 1).toUpperCase()
                        + split[i].substring(1, split[i].length());
                sb.append(tempTableName);
            }
            return sb.toString();
        }
        String tempTables = split[0].substring(0, 1).toUpperCase() + split[0].substring(1, split[0].length());
        return tempTables;
    }

    /**
     * @Description (首字母小写类名)
     * @param tableName
     * @return
     */
    public static String getClassNameToLowerName(String className) {
        if (StringUtils.isNotBlank(className)) {
            className = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
        }
        return className;
    }

    /**
     * @Description (表字段转为类字段)
     * @param name
     * @return
     */
    public static String toClassFieldName(String name) {
        if (StringUtils.isNotBlank(name)) {
            name = getClassNameToLowerName(getTableNameToClassName(name));
        }
        return name;
    }

    /**
     * @Description (转换为getSet需要的)
     * @param fieldName
     * @return
     */
    public static String toGetSetMethod(String fieldName) {
        if (StringUtils.isNotBlank(fieldName)) {
            fieldName = getClassNameToLowerName(getTableNameToClassName(fieldName));
            String maxFirst = fieldName.substring(0, 1).toUpperCase();
            String notMaxFirst = fieldName.substring(1, fieldName.length());
            fieldName = maxFirst + notMaxFirst;
        }
        return fieldName;
    }

    /**
     * @Description (Json 转换map)
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseJsonToMap(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            // 如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                @SuppressWarnings("unchecked")
                Iterator<JSONObject> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = it.next();
                    list.add(parseJsonToMap(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    /**
     * @Description (转换数据库是否为空)
     * @param isNull
     * @return
     */

    public static String getNullAble(String isNull) {

        if (("YES".equals(isNull)) || ("yes".equals(isNull)) || ("y".equals(isNull)) || ("Y".equals(isNull))) {
            return "Y";
        }

        if (("NO".equals(isNull)) || ("N".equals(isNull)) || ("no".equals(isNull)) || ("n".equals(isNull))) {
            return "N";
        }

        return null;
    }

    /**
     * @Description (tableMap 转换json)
     * @param map
     * @return
     */
    public static JSONObject mapToJson(Map<String, List<TableColumnData>> tableMap) {
        StringBuffer jsonBuffer = new StringBuffer();

        try {
            jsonBuffer.append("{\"" + "tableData" + "\"" + ":");
            jsonBuffer.append("[");
            int dataSize = 0;
            for (String data : tableMap.keySet()) {
                dataSize++;
                jsonBuffer.append("{");
                for (Field filed : data.getClass().getDeclaredFields()) {
                    jsonBuffer.append("\"" + filed.getName() + "\"" + ":" + "\"" + filed.get(data) + "\"");
                    jsonBuffer.append(",");
                }
                jsonBuffer.append("\"columnData\"" + ":[");

                int columnDataSize = 0;
                for (TableColumnData columnData : tableMap.get(data)) {
                    columnDataSize++;
                    int indexOf = 0;
                    jsonBuffer.append("{");
                    for (Field filed : columnData.getClass().getDeclaredFields()) {
                        indexOf++;
                        jsonBuffer.append("\"" + filed.getName() + "\"" + ":" + "\"" + filed.get(columnData) + "\"");
                        if (indexOf < columnData.getClass().getDeclaredFields().length) {
                            jsonBuffer.append(",");
                        }

                    }
                    jsonBuffer.append("}");
                    if (tableMap.get(data).size() > columnDataSize) {
                        jsonBuffer.append(",");
                    }

                }
                jsonBuffer.append("]");
                if (tableMap.get(data).size() > columnDataSize) {
                    jsonBuffer.append(",");
                }

                jsonBuffer.append("}");

                if (tableMap.keySet().size() > dataSize) {
                    jsonBuffer.append(",");
                }
            }
            jsonBuffer.append("]}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.fromObject(jsonBuffer.toString());
    }

    /**
     * @Description (得到项目路径)
     * @return
     */
    public static String getProjectPath() {
        String path = System.getProperty("user.dir").replace("\\", "/") + "/";
        return path;
    }

    public static void main(String[] args) {
        String t = "rttk";
        System.out.println(toGetSetMethod(t));
    }

}
