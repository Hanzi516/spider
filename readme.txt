1、 查看关于编程类的书籍,url为 https://book.douban.com/tag/编程?start=0&type=S
	start表示开始的条数,type=s表示按评分高低排序,翻页操作只需要修改start的值即可,默认每页20条数据

2、在MAX_PAGE = 100下测试：
    ConcurrentJsoupUtil(MAX_CONCURRENT = 10)
                            下载解析耗时  8173ms
                            总数量     49
                            写入成功
                            写入文件耗时  157ms
                            总耗时  8330ms
    SingleJsoupUtil
                            下载解析耗时  55964ms
                            总数量     49
                            写入成功
                            写入文件耗时  187ms
                            总耗时  56151ms

3、Executors通过invokeAll()一次批量提交多个callable

4、SpiderProxySelector设置自动代理