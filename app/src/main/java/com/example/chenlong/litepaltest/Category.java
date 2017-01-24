package com.example.chenlong.litepaltest;

import org.litepal.crud.DataSupport;

/**
 * Created by ChenLong on 2017/1/24.
 */

public class Category extends DataSupport {
    private int id;
    private String name;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
