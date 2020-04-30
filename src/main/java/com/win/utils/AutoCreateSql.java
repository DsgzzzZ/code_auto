package com.win.utils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.win.vo.TableColumnData;

@SuppressWarnings("rawtypes")
public class AutoCreateSql {

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getAutoCreateSql(String tableName, List<TableColumnData> columnDatas)
            throws Exception {

        Map sqlMap = new HashMap();
        String columns = getColumnSplit(columnDatas);
        String[] columnList = getColumnList(columns);
        String columnFields = getColumnFields(columns);
        String insert = "insert into " + tableName + "(" + columns.replaceAll("\\|", ",") + ")\n values(#{"
                + columns.replaceAll("\\|", "},#{") + "})";
        String update = getUpdateSql(tableName, columnList);
        String updateSelective = getUpdateSelectiveSql(tableName, columnDatas);
        String selectById = getSelectByIdSql(tableName, columnList);
        String delete = getDeleteSql(tableName, columnList);
        sqlMap.put("columnList", columnList);
        sqlMap.put("columnFields", columnFields);
        sqlMap.put("insert", insert.replace("#{createTime}", "now()").replace("#{updateTime}", "now()"));
        sqlMap.put("update", update.replace("#{createTime}", "now()").replace("#{updateTime}", "now()"));
        sqlMap.put("delete", delete);
        sqlMap.put("updateSelective", updateSelective);
        sqlMap.put("selectById", selectById);
        return sqlMap;
    }

    private static String getDeleteSql(String tableName, String[] columnsList) throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append("delete ");
        sb.append("\t from ").append(tableName).append(" where ");
        sb.append(columnsList[0]).append(" = #{").append(columnsList[0]).append("}");
        return sb.toString();
    }

    private static String getSelectByIdSql(String tableName, String[] columnsList) throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append("select <include refid=\"Base_Column_List\" /> \n");
        sb.append("\t from ").append(tableName).append(" where ");
        sb.append(columnsList[0]).append(" = #{").append(columnsList[0]).append("}");
        return sb.toString();
    }

    private static String getUpdateSql(String tableName, String[] columnsList) throws SQLException {

        StringBuffer sb = new StringBuffer();

        for (int i = 1; i < columnsList.length; ++i) {
            String column = columnsList[i];
            if ("CREATETIME".equals(column.toUpperCase())) {
                continue;
            }

            if ("UPDATETIME".equals(column.toUpperCase())) {
                sb.append(column + "=now()");
            } else {
                sb.append(column + "=#{" + column + "}");
            }

            if (i + 1 < columnsList.length) {
                sb.append(",");
            }
        }

        String update = "update " + tableName + " set " + sb.toString() + " where " + columnsList[0] + "=#{"
                + columnsList[0] + "}";
        return update;
    }

    private static String getUpdateSelectiveSql(String tableName, List<TableColumnData> columnList) throws SQLException {

        StringBuffer sb = new StringBuffer();
        TableColumnData cd = (TableColumnData) columnList.get(0);
        sb.append("\t<trim  suffixOverrides=\",\" >\n");
        for (int i = 1; i < columnList.size(); ++i) {
            TableColumnData data = (TableColumnData) columnList.get(i);
            String columnName = data.getColumnName();
            sb.append("\t<if test=\"").append(columnName).append(" != null ");

            if ("String" == data.getFieldType()) {
                sb.append(" and ").append(columnName).append(" != ''");
            }

            sb.append(" \">\n\t\t");
            sb.append(columnName + "=#{" + columnName + "},\n");
            sb.append("\t</if>\n");
        }
        sb.append("\t</trim>");
        String update = "update " + tableName + " set \n" + sb.toString() + " where " + cd.getColumnName() + "=#{"
                + cd.getColumnName() + "}";
        return update;
    }

    public static String getColumnSplit(List<TableColumnData> columnList) throws SQLException {

        StringBuffer commonColumns = new StringBuffer();
        for (Iterator localIterator = columnList.iterator(); localIterator.hasNext();) {
            TableColumnData data = (TableColumnData) localIterator.next();
            commonColumns.append(data.getColumnName() + "|");
        }
        return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
    }

    private static String[] getColumnList(String columns) throws SQLException {

        String[] columnList = columns.split("[|]");
        return columnList;
    }

    private static String getColumnFields(String columns) throws SQLException {

        String fields = columns;
        if ((fields != null) && (!("".equals(fields)))) {
            fields = fields.replaceAll("[|]", ",");
        }

        return fields;
    }
}
