package dev.dules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Foo {
    private int id;
    private String name;
    private Date date;
    private List<String> list;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }

    public void setList(final List<String> list) {
        this.list = list;
    }

    public void addToList(final String s){
        if(this.list == null){
            this.list = new ArrayList<>();
        }
        this.list.add(s);
    }    
}