package dev.dules;

import java.util.Date;

public class Foo {
    private int id;
    private double db;
    private String name;
    private Date date;

    public int getId() {
        return id;
    }

    public Foo setId(final int id) {
        this.id = id;
        return this;
    }

    public double getDb() {
        return db;
    }

    public Foo setDb(final double db) {
        this.db = db;
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
}