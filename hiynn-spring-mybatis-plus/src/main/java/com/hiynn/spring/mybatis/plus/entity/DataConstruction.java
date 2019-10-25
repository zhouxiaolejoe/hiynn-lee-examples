package com.hiynn.spring.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static java.util.regex.Pattern.compile;

/**
 * @ClassName DataConstruction
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/9/25 11:48
 * @Version 1.0.0
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("information_schema.COLUMNS")
public class DataConstruction {
    private String columnName;
    private String columnComment;
    private String columnType;
    private String columnLengh;
//    @JsonIgnore
    private String defaultValue;
//    @JsonIgnore
    private Integer isKey;
//    @JsonIgnore
    private Integer isEmpty;;

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getIsKey() {
        return isKey;
    }

    public void setIsKey(Integer isKey) {
        this.isKey = isKey;
    }

    public Integer getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(Integer isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
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

    public String getColumnLengh() {
        return columnLengh;
    }

    public void setColumnLengh(String columnLengh) {
        String lengh = compile("[^0-9]").matcher(columnLengh).replaceAll("");
        if ("".equals(lengh)){
            lengh = "0";
        }
        this.columnLengh =lengh;
    }
}
