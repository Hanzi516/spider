package com.jiangzh.util;

import com.jiangzh.constants.Constants;
import com.jiangzh.model.BookInfo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangzh
 */
public class SingleJsoupUtil {
    /**
     * 单例模式创建 JsoupUtil对象
     */
    private SingleJsoupUtil() {
    }

    private static final SingleJsoupUtil instance = new SingleJsoupUtil();

    public static SingleJsoupUtil getInstance() {
        return instance;
    }

    /**
     *
     */
    public List<BookInfo> getDoubanBookInfo() {
        List<BookInfo> bookInfoLists = new ArrayList<BookInfo>();
        try {
            for (int i = 0; i < Constants.MAX_PAGE; ++i) {
                System.out.println("目前   i="+i);
                String url = Constants.URL + Constants.START + String.valueOf(i * Constants.NUM) + "&type=S";
                System.out.println(url);
                Connection connection = Jsoup.connect(url);
                Document document = connection.get();
                Elements ul = document.select("div.info"); // 得到ul标签,标签中包含了一本书的所有信息
                bookInfoLists.addAll(ParseDocument.parse(ul));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookInfoLists;
    }
}
