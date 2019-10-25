package com.hiynn.spring.mybatis.plus.config;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @ClassName DataBaseTableColumn
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/21 9:22
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DataBaseTableColumn {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列的数据类型
     */
    private String dataType;
    /**
     * 注释
     */
    private String comments;
}
