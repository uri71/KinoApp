package com.mozidev.kino.model;

import java.io.Serializable;

/**
 * Created by y.storchak on 06.01.15.
 */
public class Team implements Serializable{

    public String name;
    public String line;
    public String uri;
    public String image;
    public String[] films;
    public String about;
    public String rating;


    public Team(String name, String line, String uri, String about, String rating, String image, String [] films) {
        this.name = name;
        this.line = line;
        this.uri = uri;
        this.image = image;
        this.films = films;
        this.about = about;
        this.rating = rating;
    }
}
