package com.mozidev.kino.model;

/**
 * Created by y.storchak on 12.01.15.
 */
public class Item {

    public String name;
    public String data;
    public int type;




    public Item(int type, String name, String data) {
        this.type = type;
        this.name = name;
        this.data = data;
    }
}
