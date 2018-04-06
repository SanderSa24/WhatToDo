package com.example.sander.whattodo.DB;

import java.io.Serializable;

//Implements standard Java interface.
//Represents one single activity in the app
public class ToDo implements Serializable{

    private int id;
    private String title;
    private String type;
    private String info;

    public ToDo(){


    }
    public ToDo(String title, String type, String info) {

        this(-1,title,type,info);
    }

    public ToDo(int id, String title, String type, String info) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.info = info;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
