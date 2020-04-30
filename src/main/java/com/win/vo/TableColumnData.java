package com.win.vo;

import com.win.utils.CommonsUtils;

/**
 * @ClassName TableColumnData
 * @Description TODO(表字段数据)
 * @author huiziqin
 * @Date 2018年4月21日 下午4:35:57
 * @version 1.0.0
 */
public class TableColumnData {

    /**
     * 数据库-字段名称
     */
    public String columnName;

    /**
     * 字段名
     */
    public String fieldName;
    /**
     * 字段描述
     */
    public String columnComment;
    /**
     * 数据库类型
     */
    public String columnType;

    /**
     * 字段类型
     */
    public String fieldType;

    /**
     * 最大长度
     */
    public Long   maxLength;
    /**
     * 是否可以为空
     */
    public String isNull;

    public String key;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = CommonsUtils.toClassFieldName(fieldName);
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Long maxLength) {
        this.maxLength = maxLength;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = CommonsUtils.getNullAble(isNull);
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TableColumnData [columnName=" + columnName + ", fieldName=" + fieldName + ", columnComment="
               + columnComment + ", columnType=" + columnType + ", fieldType=" + fieldType + ", maxLength=" + maxLength
               + ", isNull=" + isNull + ", key=" + key + "]";
    }

}
