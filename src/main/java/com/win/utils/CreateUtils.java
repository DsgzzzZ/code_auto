package com.win.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.win.vo.DataBaseVO;
import com.win.vo.TableColumnData;

/**
 * @ClassName CommonUtils
 * @Description TODO(公用方法类)
 * @author huiziqin
 * @Date 2018年4月17日 下午6:40:03
 * @version 1.0.0
 */
public class CreateUtils {

    /**
     * 数据库连接驱动名称
     */
    public static final String CONNECTION        = "CONNECTION";

    /**
     * 数据库连接驱动名称
     */
    public static final String DATABASEVO        = "DATABASEVO";

    /**
     * 表数据
     */
    public static final String TABLEDATA         = "TABLEDATA";
    /**
     * 表数据
     */
    public static final String NOTJSON_TABLEDATA = "NOTJSON_TABLEDATA";

    /**
     * 查询数据库表sql
     */
    static String              queryTableSql     = null;

    /**
     * @Description (设置表数据)
     * @param session
     */
    public static Map<String, List<TableColumnData>> setTableData(Connection connection, DataBaseVO dataBaseVO) {

        try {
            if (connection != null) {
                Map<String, List<TableColumnData>> tableMap = new HashMap<>();
                String dataBaseName = dataBaseVO.getDataBaseName();
                Statement st = connection.createStatement();
                ResultSet set = st.executeQuery(setQueryTableSql(dataBaseName));
                while (set.next()) {
                    String tableName = set.getString("table_name").replace(" ", "").trim();
                    String tableComment = set.getString("table_comment").replace(" ", "").trim();
                    tableComment = tableComment == null || tableComment.equals("") ? "-" : tableComment;
                    List<TableColumnData> columnData = setColumnDatas(connection, tableName, dataBaseName);
                    tableMap.put(tableName + "∞" + tableComment, columnData);
                }
                return tableMap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description (得到表详情)
     * @param tableName
     * @return
     * @throws SQLException
     */
    private static List<TableColumnData> setColumnDatas(Connection connection, String tableName,
                                                        String dataBaseName) throws Exception {

        PreparedStatement statement = connection.prepareStatement(setQueryTableColumnDatasSql(tableName, dataBaseName));
        ResultSet resultSet = statement.executeQuery();
        List<TableColumnData> dataList = new ArrayList<>();
        while (resultSet.next()) {
            TableColumnData tableColumnData = new TableColumnData();
            tableColumnData.setColumnName(resultSet.getString(1));
            tableColumnData.setColumnType(resultSet.getString(2));
            tableColumnData.setColumnComment(resultSet.getString(3));
            tableColumnData.setMaxLength(resultSet.getString(6) == null ? 0L : resultSet.getLong(6));
            tableColumnData.setIsNull(resultSet.getString(7));
            tableColumnData.setFieldName(CommonsUtils.toClassFieldName(resultSet.getString(1)));
            String precision = resultSet.getString(4);
            String scale = resultSet.getString(5);
            tableColumnData.setFieldType(getFieldType(resultSet.getString(2), precision, scale));
            tableColumnData.setKey(resultSet.getString(8));
            dataList.add(tableColumnData);
        }
        return dataList;
    }

    /**
     * @Description (得到类型)
     * @param dataType
     * @param precision
     * @param scale
     * @return
     */
    private static String getFieldType(String dataType, String precision, String scale) {

        dataType = dataType.toLowerCase();
        if (dataType.contains("char") || dataType.equals("timestamp")) {
            dataType = "String";
        } else if (dataType.equals("bigint")) {
            dataType = "Long";
        } else if (dataType.contains("int")) {
            dataType = "Integer";
        } else if (dataType.contains("float")) {
            dataType = "Float";
        } else if (dataType.contains("double")) {
            dataType = "Double";
        } else if (dataType.contains("number")) {
            if ((StringUtils.isNotBlank(scale)) && (Integer.parseInt(scale) > 0)) {
                dataType = "BigDecimal";
            } else if ((StringUtils.isNotBlank(precision)) && (Integer.parseInt(precision) > 6)) {
                dataType = "Long";
            } else {
                dataType = "Integer";
            }
        } else if (dataType.contains("decimal")) {
            dataType = "BigDecimal";
        } else if (dataType.contains("date")) {
            dataType = "Date";
        } else if (dataType.contains("time")) {
            dataType = "Timestamp";
        } else if (dataType.contains("clob")) {
            dataType = "Clob";
        } else {
            dataType = "Object";
        }

        return dataType;
    }

    /**
     * @Description (得到fileds详情)
     * @param tableName
     * @param isVO
     * @return
     * @throws SQLException
     */
    public static String getBeanFeilds(List<TableColumnData> columnDataList, boolean isVO) throws SQLException {
        StringBuffer sb = new StringBuffer();
        StringBuffer getSet = new StringBuffer();
        int index = 0;
        for (TableColumnData tableColumnData : columnDataList) {
            index++;
            String columnName = tableColumnData.getColumnName();
            String columnComment = tableColumnData.getColumnComment();
            // 实体类
            String fieldName = tableColumnData.getFieldName();
            String fieldType = tableColumnData.getFieldType();

            sb.append("/**").append("\r\t");
            sb.append("*").append(columnComment).append("\r\t");
            sb.append("**/").append("\r\t");
            if (!isVO) {
                if (index == 1) {
                    // ID列
                    // sb.append(" @TableId(\"" + columnName + "\")").append("\r\t");
                } else {
                    // sb.append(" @TableField(\"" + columnName + "\")").append("\r\t");
                }
            }
            if(isVO){
                if(fieldName.equals("version") || fieldName.equals("tbStatus")){
                    sb.append("@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)").append("\r\t");
                }
            }
            if( !isVO &&
                index != 1 &&
                tableColumnData.getIsNull().equals("N") &&
                !fieldName.equals("version") &&
                !fieldName.equals("createTime") &&
                !fieldName.equals("createPerson") &&
                !fieldName.equals("updateTime") &&
                !fieldName.equals("updatePerson") &&
                !fieldName.equals("tbStatus")
            ){
                if(fieldType.equals("Integer") || fieldType.equals("Long")){
                    sb.append("@NotNull(message = \""+columnComment+"不能为空\")").append("\r\t");
                }else{
                    sb.append("@NotEmpty(message = \""+columnComment+"不能为空\")").append("\r\t");
                }
            }
            sb.append("private ").append(fieldType + " ").append(fieldName).append(";\r\t");

            String getSetFiledName = CommonsUtils.toGetSetMethod(fieldName);
            getSet.append("\r\t").append("public ");
            getSet.append(fieldType + " ").append("get" + getSetFiledName + "() {\r\t");
            getSet.append("    return this.").append(fieldName).append(";\r\t}");
            getSet.append("\r\t").append("public void ");
            getSet.append("set" + getSetFiledName + "(" + fieldType + " " + fieldName + ") {\r\t");
            getSet.append("    this." + fieldName + "=").append(fieldName).append(";\r\t}");

        }

//        if(isVO){
//            sb.append("/**").append("\r\t");
//            sb.append("*").append("最小创建时间").append("\r\t");
//            sb.append("**/").append("\r\t");
//            sb.append("private ").append("String ").append("minCreateTime").append(";\r\t");
//
//            sb.append("/**").append("\r\t");
//            sb.append("*").append("最大创建时间").append("\r\t");
//            sb.append("**/").append("\r\t");
//            sb.append("private ").append("String ").append("maxCreateTime").append(";\r\t");
//
//            sb.append("/**").append("\r\t");
//            sb.append("*").append("最小更新时间").append("\r\t");
//            sb.append("**/").append("\r\t");
//            sb.append("private ").append("String ").append("minUpdateTime").append(";\r\t");
//
//            sb.append("/**").append("\r\t");
//            sb.append("*").append("最大更新时间").append("\r\t");
//            sb.append("**/").append("\r\t");
//            sb.append("private ").append("String ").append("maxUpdateTime").append(";\r\t");
//
//            String getSetFiledName = CommonsUtils.toGetSetMethod("minCreateTime");
//            getSet.append("\r\t").append("public ");
//            getSet.append("String ").append("get" + getSetFiledName + "() {\r\t");
//            getSet.append("    return this.").append("minCreateTime").append(";\r\t}");
//            getSet.append("\r\t").append("public void ");
//            getSet.append("set" + getSetFiledName + "(" + "String" + " " + "minCreateTime" + ") {\r\t");
//            getSet.append("    this." + "minCreateTime" + "=").append("minCreateTime").append(";\r\t}");
//
//            getSetFiledName = CommonsUtils.toGetSetMethod("maxCreateTime");
//            getSet.append("\r\t").append("public ");
//            getSet.append("String ").append("get" + getSetFiledName + "() {\r\t");
//            getSet.append("    return this.").append("maxCreateTime").append(";\r\t}");
//            getSet.append("\r\t").append("public void ");
//            getSet.append("set" + getSetFiledName + "(" + "String" + " " + "maxCreateTime" + ") {\r\t");
//            getSet.append("    this." + "maxCreateTime" + "=").append("maxCreateTime").append(";\r\t}");
//
//            getSetFiledName = CommonsUtils.toGetSetMethod("minUpdateTime");
//            getSet.append("\r\t").append("public ");
//            getSet.append("String ").append("get" + getSetFiledName + "() {\r\t");
//            getSet.append("    return this.").append("minUpdateTime").append(";\r\t}");
//            getSet.append("\r\t").append("public void ");
//            getSet.append("set" + getSetFiledName + "(" + "String" + " " + "minUpdateTime" + ") {\r\t");
//            getSet.append("    this." + "minUpdateTime" + "=").append("minUpdateTime").append(";\r\t}");
//
//            getSetFiledName = CommonsUtils.toGetSetMethod("maxUpdateTime");
//            getSet.append("\r\t").append("public ");
//            getSet.append("String ").append("get" + getSetFiledName + "() {\r\t");
//            getSet.append("    return this.").append("maxUpdateTime").append(";\r\t}");
//            getSet.append("\r\t").append("public void ");
//            getSet.append("set" + getSetFiledName + "(" + "String" + " " + "maxUpdateTime" + ") {\r\t");
//            getSet.append("    this." + "maxUpdateTime" + "=").append("maxUpdateTime").append(";\r\t}");
//        }

        /*
         * if (!isVO) { String ObjectStr = columnDataList.get(0).getFieldName(); getSet.append(" @Override\r\t");
         * getSet.append("protected Serializable pkVal() {\r\t"); getSet.append("return this." + ObjectStr + ";\r\t");
         * getSet.append("}"); }
         */
        return sb.toString() + getSet.toString();
    }

    /**
     * @Description (设置查询表字段数据sql)
     * @param tableName
     */
    private static String setQueryTableColumnDatasSql(String tableName, String dataBaseName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select column_name ,data_type,column_comment,0,0,character_maximum_length,is_nullable nullable,COLUMN_KEY keybase ");
        sb.append("from information_schema.columns where table_name =  '");
        sb.append(tableName);
        sb.append("' and table_schema =  '");
        sb.append(dataBaseName);
        sb.append("'");
        return sb.toString();
    }

    /**
     * @Description (设置查询表sql)
     * @param session
     */
    private static String setQueryTableSql(String dataBaseName) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT table_name,table_comment FROM information_schema.TABLES WHERE ");
        sb.append("table_schema= '");
        sb.append(dataBaseName);
        sb.append("' AND table_type='base table'");
        return sb.toString();
    }

}
