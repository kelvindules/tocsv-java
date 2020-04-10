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

    public Foo setId(final int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Foo setName(final String name) {
        this.name = name;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Foo setDate(final Date date) {
        this.date = date;
        return this;
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }

    public Foo setList(final List<String> list) {
        this.list = list;
        return this;
    }

    public void addToList(final String s){
        if(this.list == null){
            this.list = new ArrayList<>();
        }
        this.list.add(s);
    }    
}