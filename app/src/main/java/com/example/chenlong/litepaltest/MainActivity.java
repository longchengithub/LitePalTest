package com.example.chenlong.litepaltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button_db)
    Button mDb;
    @BindView(R.id.button_insert)
    Button mInsert;
    @BindView(R.id.button_delete)
    Button mDelete;
    @BindView(R.id.button_update)
    Button mUpdate;
    @BindView(R.id.button_select)
    Button mSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /**
         * litepal操作数据库
         */
        mDb.setOnClickListener(v -> {
            LitePal.getDatabase();
        });

        /**
         * litepal添加数据到数据库
         */
        mInsert.setOnClickListener(v -> {
            Book book = new Book();
            book.setName("第一行代码");
            book.setAuthor("郭林");
            book.setPages(581);
            book.setPrice(79.01);
            book.setPress("");
            book.save();        //保存操作

            Toast.makeText(this, "添加一条数据完毕", Toast.LENGTH_SHORT).show();
        });

        /**
         * litepal更新数据
         *
         * 使用litepal的 orm操作时  需要注意很重要的问题
         * 因为使用的是java类bean    所以所有的字段都是有默认值的 int是0 String是null boolean是false等
         * 所以想要更新一些值为java的默认值 比如 book.setPrice(0);是无效的
         * 如果想要实现更新一些默认值 必须使用api的  book.setToDefault("数据库的列名");
         *
         */
        mUpdate.setOnClickListener(v -> {
            Book book = new Book();
            book.setPrice(18.88);
            book.setPress("hehe");
            book.updateAll("name = ? and author = ?", "第一行代码", "郭林");

            Toast.makeText(this, "更新完毕!", Toast.LENGTH_SHORT).show();
        });

        /**
         * litepal的删除
         */
        mDelete.setOnClickListener(v -> {
            DataSupport.deleteAll(Book.class, "name = ? and price = ?", "第一行代码", "18.88");

            Toast.makeText(this, "删除成功!", Toast.LENGTH_SHORT).show();
        });

        /**
         * litepal的查询
         * 关于查询的方法 根据不同的场景 有很多api
         */
        mSelect.setOnClickListener(v -> {
            List<Book> books = DataSupport.findAll(Book.class); //查询出所有的代码
            for (Book book : books) {
                Toast.makeText(this, book.getName(), Toast.LENGTH_SHORT).show();
            }

            /**
             * litepal的一些api

             //简单的获取第一行或者最后一行
             Book first = DataSupport.findFirst(Book.class);
             Book last = DataSupport.findLast(Book.class);

             //select查询 比如只查询出Book对象的name和author2列的数据
             List<Book> books1 = DataSupport.select("name", "author").find(Book.class);

             //where查询   比如只查询价格大于100的Book对象
             List<Book> books2 = DataSupport.where("price > ?", "100").find(Book.class);

             //order排序   desc降序 asc升序 同sqlite的 order by 用法
             List<Book> books3 = DataSupport.order("price desc").find(Book.class);

             //limit指定数量
             List<Book> books4 = DataSupport.limit(3).find(Book.class);

             //offset偏移查询  比如查询的是从第5条数据后面的8条数据
             DataSupport.offset(5).limit(8).find(Book.class);

             //以上的操作符是可以任意连缀组合的
             DataSupport.select("name", "author", "pages")
             .where("pages ?", "200")
             .order("price desc")
             .limit(10)
             .offset(3)
             .find(Book.class);

             //litepal的api如果还是无法满足你的要求  还是支持原生的sql查询
             Cursor cursor = DataSupport.findBySQL("select * from Book " +
             "where pages > ? " +
             "and price < ?"
             , "200", "20");

             */
        });

    }
}
