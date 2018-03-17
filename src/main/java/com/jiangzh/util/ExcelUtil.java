package com.jiangzh.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jiangzh.model.BookInfo;
/**
 * excel写入工具
 * @author jiangzh
 */
public class ExcelUtil {
	@SuppressWarnings("unchecked")
	public static void writeExcel(List<BookInfo> bookInfoLists) {
		if(bookInfoLists==null || bookInfoLists.isEmpty()){
            System.out.println("数据为空。");
            return;
        }
//		Collections.sort(bookInfoLists, new ExcelUtil().new SortByPrice());
		
		//第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("豆瓣高分编程书籍");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("书名");
        row.createCell(2).setCellValue("评分");
        row.createCell(3).setCellValue("评价人数");
        row.createCell(4).setCellValue("作者");
        row.createCell(5).setCellValue("出版物");
        row.createCell(6).setCellValue("出版日期");
        row.createCell(7).setCellValue("价格");
        for (int i = 0; i < bookInfoLists.size(); i++) {
        	int j = 0;
            HSSFRow row1 = sheet.createRow(i + 1);
            BookInfo bookInfo = bookInfoLists.get(i);
            //创建单元格设值
            row1.createCell(j++).setCellValue(i+1);
            row1.createCell(j++).setCellValue(bookInfo.getBookName());
            row1.createCell(j++).setCellValue(bookInfo.getScore());
            row1.createCell(j++).setCellValue(bookInfo.getEvaluPeople());
            row1.createCell(j++).setCellValue(bookInfo.getAuthor());
            row1.createCell(j++).setCellValue(bookInfo.getPublisher());
            row1.createCell(j++).setCellValue(bookInfo.getPublishDate());
            row1.createCell(j).setCellValue(bookInfo.getPrice());
        }
        //将文件保存到指定的位置
        try {
            FileOutputStream fos = new FileOutputStream("豆瓣.xls");
            workbook.write(fos);
            System.out.println("写入成功");
            workbook.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * 根据价格排序,由高到低排序
	 * @author jiangzh
	 */
	@SuppressWarnings("rawtypes")
	class SortByPrice implements Comparator{
		@Override
		public int compare(Object o1, Object o2) {
			BookInfo book1 = (BookInfo) o1;
			BookInfo book2 = (BookInfo) o2;
			if(FloatReg.parseString(book1.getPrice()) <= FloatReg.parseString(book2.getPrice())){
				return 1;
			}
			return -1;
		}
	}
}	
