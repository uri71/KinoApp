package com.mozidev.kino.model;

import java.io.Serializable;

/**
 * Created by y.storchak on 06.01.15.
 */
public class Team implements Serializable{

    public String name;
    public String line;
    public String image;
    public String[] films;
    public String about;


    public Team(String name, String line,  String about,  String image, String [] films) {
        this.name = name;
        this.line = line;
        this.image = image;
        this.films = films;
        this.about = about;
    }
}
