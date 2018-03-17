package com.jiangzh.launch;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.jiangzh.model.BookInfo;
import com.jiangzh.util.*;

/**
 * 启动类
 *
 * @author jiangzh
 */
public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
//        JsoupUtil ju = JsoupUtil.getInstance();
//		ProxyJsoupUtil ju = ProxyJsoupUtil.getInstance();
//        ProxySelectJsoup ju = ProxySelectJsoup.getInstance();
        ConcurrentJsoupUtil ju = ConcurrentJsoupUtil.getInstance();
//        SingleJsoupUtil ju = SingleJsoupUtil.getInstance();
        List<BookInfo> bookInfoLists = ju.getDoubanBookInfo();
        long endTime = System.currentTimeMillis();
        System.out.println("下载解析耗时  " + (endTime - startTime) + "ms");
        System.out.println("总数量     "+bookInfoLists.size());
        ExcelUtil.writeExcel(
                bookInfoLists.stream()
                        .sorted(Comparator.comparing(BookInfo::getScore).reversed())
                        .limit(40)
                        .collect(Collectors.toList())
        );
        long endTime2 = System.currentTimeMillis();
        System.out.println("写入文件耗时  " + (endTime2 - endTime) + "ms");
        System.out.println("总耗时  " + (endTime2 - startTime) + "ms");

    }
}
