package com.jiangzh.model;

/**
 * 书籍信息实体类
 * @author jiangzh
 */
public class BookInfo {
	/** 书名 */
	private String bookName;
	/** 评分 */
	private float score;
	/** 评价人数 */
	private int evaluPeople;
	/** 作者 */
	private String author;
	/** 出版社 */
	private String publisher;
	/** 出版日期 */
	private String publishDate;
	/** 价格 */
	private String price;
	
	public BookInfo() {
		super();
	}
	public BookInfo(String bookName, float score, int evaluPeople, String author, String publisher, String publishDate,
			String price) {
		super();
		this.bookName = bookName;
		this.score = score;
		this.evaluPeople = evaluPeople;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "BookInfo [bookName=" + bookName + ", score=" + score + ", evaluPeople=" + evaluPeople + ", author="
				+ author + ", publisher=" + publisher + ", publishDate=" + publishDate + ", price=" + price + "]";
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getEvaluPeople() {
		return evaluPeople;
	}
	public void setEvaluPeople(int evaluPeople) {
		this.evaluPeople = evaluPeople;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
