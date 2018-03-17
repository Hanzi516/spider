package com.jiangzh.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jiangzh.constants.Constants;
import com.jiangzh.model.BookInfo;

/**
 * Jsoup解析工具
 * @author jiangzh
 */
public class JsoupUtil {  
	/** 单例模式创建 JsoupUtil对象*/
    private JsoupUtil() {  
    }  
    private static final JsoupUtil instance = new JsoupUtil();  
  
    public static JsoupUtil getInstance() {  
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
                String url = Constants.URL + Constants.START + String.valueOf(i * Constants.NUM)+"&type=S";  
                System.out.println(url);  
                Connection connection = Jsoup.connect(url);  
                Document document = connection.get();  
                Elements ul = document.select("div.info"); // 得到ul标签,标签中包含了一本书的所有信息
                if (ul.isEmpty()) {
                    break;
                }
                bookInfoLists.addAll(ParseDocument.parse(ul));
                i++;
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return bookInfoLists;
    }
      
}  