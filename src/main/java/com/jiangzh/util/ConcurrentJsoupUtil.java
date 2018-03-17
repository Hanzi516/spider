package com.jiangzh.util;

import com.jiangzh.constants.Constants;
import com.jiangzh.model.BookInfo;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author jiangzh
 */
public class ConcurrentJsoupUtil {

    /**
     * 线程共享变量
     */
    private static int shareNum = 0;

    /**
     * 单例模式创建 ConcurrentJsoupUtil对象
     */
    private ConcurrentJsoupUtil() {
    }

    private static final ConcurrentJsoupUtil instance = new ConcurrentJsoupUtil();

    public static ConcurrentJsoupUtil getInstance() {
        return instance;
    }

    /**
     *
     */
    public List<BookInfo> getDoubanBookInfo() {
        List<BookInfo> bookInfoLists = new ArrayList<BookInfo>();
        ExecutorService executorService = Executors.newWorkStealingPool();
        List<Callable<List<BookInfo>>> callable = new ArrayList<>();
        for (int t = 0; t < Constants.MAX_CONCURRENT; ++t) {
            callable.add(concurrentDoWork());
        }
        try {
            executorService.invokeAll(callable)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            ExceptionUtils.getStackTrace(e);
                            return new ArrayList<BookInfo>();
                        }
                    })
                    .forEach(bookInfoLists::addAll);
        } catch (InterruptedException e) {
            ExceptionUtils.getStackTrace(e);
        }
        return bookInfoLists;
    }

    private Callable<List<BookInfo>> concurrentDoWork() {
        return () -> {
            List<BookInfo> result = new ArrayList<>();
            while (true) {
                int i = incShareNum();
                System.out.println("shareNum=" + i);
                if (i > Constants.MAX_PAGE) {
                    break;
                }
                result.addAll(doWork(i));
            }
            return result;
        };
    }

    private List<BookInfo> doWork(int i) {
        String url = Constants.URL + Constants.START + String.valueOf(i * Constants.NUM) + "&type=S";
        System.out.println(url);
        Connection connection = Jsoup.connect(url);
        Elements elements = null;
        for (int t = 0; t < Constants.RETRY_TIMES; ++t) {
            try {
                // 得到ul标签,标签中包含了一本书的所有信息
                elements = getDocument(connection).select("div.info");
                break;
            } catch (Exception e) {
                System.out.println(ExceptionUtils.getStackTrace(e));
            }
        }
        if (elements == null || elements.isEmpty()) {
            return new ArrayList<>();
        }
        return ParseDocument.parse(elements);
    }

    private Document getDocument(Connection con) throws Exception {
        return con.get();
    }

    private synchronized int incShareNum() {
        int i = shareNum;
        ++shareNum;
        return i;
    }
}
