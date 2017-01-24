# LitePalTest 配置LitePal
1. compile 'org.litepal.android:core:1.4.1' 依赖
2. 新建一个litepal.xml文件 在assets目录下
    <?xml version="1.0" encoding="utf-8"?>
    <litepal>
       <dbname value="BookStore"></dbname>
       <version value="2"></version>

       <list>
           <mapping class="com.example.chenlong.litepaltest.Book"></mapping>
           <mapping class="com.example.chenlong.litepaltest.Category"></mapping>
       </list>
    </litepal>
    
    dbname:顾名思义 表示的是数据库的名称
    version:版本号 升级数据库的时候用到
    list:orm 映射的 javaBean 的全类名
   
3. 需要修改application标签  android:name="org.litepal.LitePalApplication"

4. 创建数据库的api为 LitePal.getDatabase();

5. CRUD的操作api可以看demo 不详细说了 基于javabean的操作 不通过执行sql语句或者cursor
    CRUD的操作 记住javaBean类 需要继承自LitePal的DataSupport 比如:public class Book extends DataSupport
