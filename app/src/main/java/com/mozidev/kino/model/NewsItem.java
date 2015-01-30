package com.mozidev.kino.model;

/**
 * Created by y.storchak on 29.01.15.
 */
public class NewsItem {
    public int photo_1;
    public int photo_2;
    public int photo_3;
    public String title;


    public NewsItem(int photo_1, int photo_2, int photo_3, String title) {
        this.photo_1 = photo_1;
        this.photo_2 = photo_2;
        this.photo_3 = photo_3;
        this.title = title;
    }
}
