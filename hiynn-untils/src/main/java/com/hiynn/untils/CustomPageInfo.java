package com.hiynn.untils;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Description 用于前端分页返回
 * @Author ZhouXiaoLe
 * @Date 2019/7/17  16:28
 * @Param
 * @return
 **/
@Getter
@Setter
@ToString
public class CustomPageInfo<T> {
    private List<T> list;
    private InnerPageInfo pageInfo = new InnerPageInfo();

    public static <T> CustomPageInfo<T> of(PageInfo<T> pageInfo) {
        CustomPageInfo<T> customPageInfo = new CustomPageInfo<>();
        customPageInfo.list = pageInfo.getList();
        customPageInfo.pageInfo.currentPage = pageInfo.getPageNum();
        customPageInfo.pageInfo.totalCount = pageInfo.getTotal();
        customPageInfo.pageInfo.pageSize = pageInfo.getSize();
        return customPageInfo;
    }

    @Getter
    @Setter
    @ToString
    public class InnerPageInfo {
        int currentPage;
        long totalCount;
        long pageSize;
    }

}
