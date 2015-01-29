package com.mozidev.kino.model;

import java.io.Serializable;

/**
 * Created by y.storchak on 17.01.15.
 */
public class Photo implements Serializable{
    public int photo;
    public String caption;


    public Photo(int photo, String caption) {
        this.photo = photo;
        this.caption = caption;
    }
}
