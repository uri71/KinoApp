package com.mozidev.kino.model;

/**
 * Created by y.storchak on 06.01.15.
 */
public class Team {

    public String name;
    public String line;
    public String uri;
    public int image;


    public Team(String name, String line, String uri, int image) {
        this.name = name;
        this.line = line;
        this.uri = uri;
        this.image = image;
    }
}
