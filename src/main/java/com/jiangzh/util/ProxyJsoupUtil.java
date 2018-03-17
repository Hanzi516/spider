package com.jiangzh.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.jiangzh.constants.Constants;
import com.jiangzh.model.BookInfo;

/**
 * @author jiangzh
 */
public class ProxyJsoupUtil {
	/** 无代理ip测试 此项没有做测试 */
	private String ip = "117.90.7.83";
	private int port = 	9000;
	/** 单例模式创建 JsoupUtil对象*/
    private ProxyJsoupUtil() {  
    }  
    private static final ProxyJsoupUtil instance = new ProxyJsoupUtil();  
  
    public static ProxyJsoupUtil getInstance() {  
        return instance;  
    }  
    /**
     * 
     */
    public List<BookInfo> getDoubanBookInfo(){ 
    	List<BookInfo> bookInfoLists = new ArrayList<BookInfo>();
        try {  
        	int i=0;
            while(true) {
            	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
                String href = Constants.URL + Constants.START + String.valueOf(i * Constants.NUM)+"&type=S";  
                URL url = new URL(href); 
                HttpsURLConnection urlcon = (HttpsURLConnection)url.openConnection(proxy);  
                urlcon.connect();         //获取连接  
                InputStream is = urlcon.getInputStream();  
                BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
                StringBuffer content = new StringBuffer();  
                String line = null;  
                while((line=buffer.readLine())!=null){  
                	content.append(line);  
                }  
                Document document = Jsoup.parse(content.toString()); 
                Elements ul = document.select("div.info"); // 得到ul标签,标签中包含了一本书的所有信息
                bookInfoLists.addAll(ParseDocument.parse(ul));
                i++;
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bookInfoLists;
    }
}
