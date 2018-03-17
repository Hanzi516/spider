package com.jiangzh.constants;

/**
 * 静态量
 *
 * @author jiangzh
 */
public final class Constants {
    /**
     * 豆瓣最受欢迎的编程书籍URL
     */
    public static final String URL = "https://book.douban.com/tag/编程";
    /**
     * 每页显示记录条数
     */
    public static final int NUM = 20;
    /**
     * 拼接分页
     */
    public static final String START = "?start=";

    /**
     * 地址
     */
    public static final String IP = "117.90.7.83";

    /**
     * 端口
     */
    public static final int PORT = 9000;

    /**
     * 最大任务数
     */
    public static final int MAX_CONCURRENT = 10;

    /**
     * 最大页码
     */
    public static final int MAX_PAGE = 100;

    /**
     * 重试次数
     */
    public static final int RETRY_TIMES = 3;
}
