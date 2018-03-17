package com.jiangzh.util;

import com.jiangzh.constants.Constants;
import com.jiangzh.model.BookInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动代理
 * @author jiangzh
 */
public class ProxySelectJsoup {

    /**
     * 单例模式创建 JsoupUtil对象
     */
    private ProxySelectJsoup() {
    }

    private static final ProxySelectJsoup instance = new ProxySelectJsoup();

    public static ProxySelectJsoup getInstance() {
        return instance;
    }

    /**
     *
     */
    public List<BookInfo> getDoubanBookInfo() {
        List<BookInfo> bookInfoLists = new ArrayList<>();
        try {
            int i = 0;
            while (true) {
                SpiderProxySelector spiderProxySelector = new SpiderProxySelector();
                ProxySelector.setDefault(spiderProxySelector);
                // 获取到全局代理
                ProxySelector ps = ProxySelector.getDefault();
                // 获取到代理的列表
                List<Proxy> proxyList = ps.select(URI.create("/"));
                String href = Constants.URL + Constants.START + String.valueOf(i * Constants.NUM) + "&type=S";
                URL url = new URL(href);
                HttpsURLConnection urlcon = (HttpsURLConnection) url.openConnection(proxyList.get(0));
                urlcon.connect();
                InputStream is = urlcon.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                StringBuffer content = new StringBuffer();
                String line = null;
                while ((line = buffer.readLine()) != null) {
                    content.append(line);
                }
                Document document = Jsoup.parse(content.toString());
                Elements ul = document.select("div.info"); // 得到ul标签,标签中包含了一本书的所有信息
                if (ul.isEmpty()) {
                    break;
                }
                bookInfoLists.addAll(ParseDocument.parse(ul));
                i++;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return bookInfoLists;
    }
}
