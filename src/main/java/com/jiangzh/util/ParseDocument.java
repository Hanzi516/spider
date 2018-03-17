package com.jiangzh.util;

import com.jiangzh.model.BookInfo;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author jiangzh
 */
public final class ParseDocument {

    public static List<BookInfo> parse(Elements ul) {
        System.out.println("开始解析");
        if (ul==null ||ul.isEmpty()){
            return new ArrayList<>();
        }
        List<BookInfo> result=new ArrayList<>();
        Iterator<Element> ulIter = ul.iterator();
        BookInfo bookInfo = null;
        while (ulIter.hasNext()) {
            //获取元素
            Element element = ulIter.next();
            bookInfo = new BookInfo();
            //书名
            String bookName = element.select("h2 > a").attr("title");
            bookInfo.setBookName(bookName);
            //评价人数
            String evaluPeopleStr = element.select(".pl").text();
            int evaluPeople = Integer.parseInt(evaluPeopleStr.replaceAll("[^0-9]", ""));
            if (evaluPeople < 1000) {
                System.out.println(bookName + "评价人数太少,不做收录");
                continue;
            }
            bookInfo.setEvaluPeople(evaluPeople);
            //评分
            String score = element.select(".rating_nums").text();
            bookInfo.setScore(Float.parseFloat(score));
            String bookBaseInfo = element.select(".pub").text();
            String a[] = bookBaseInfo.split("/");
            if(a.length==5){
                //作者
                bookInfo.setAuthor(a[0]);
                //出版社
                bookInfo.setPublisher(a[2]);
                //出版时间
                bookInfo.setPublishDate(a[3]);
                //价格
                bookInfo.setPrice(a[4]);
                result.add(bookInfo);
            }else if(a.length==4){
                //作者
                bookInfo.setAuthor(a[0]);
                //出版社
                bookInfo.setPublisher(a[1]);
                //出版时间
                bookInfo.setPublishDate(a[2]);
                //价格
                bookInfo.setPrice(a[3]);
                result.add(bookInfo);
            }else {
                System.out.println(bookName + "的基本信息有误,此条不收录");
                System.out.println(bookBaseInfo);
            }
            System.out.println(bookInfo.toString());
        }
        System.out.println("解析结束");
        return result;
    }
}
