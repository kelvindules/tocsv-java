package dev.dules;

import java.util.Calendar;

public final class App {
    private App() {
    }

    public static void main(final String[] args) {
        final Foo foo = new Foo().setId(1).setDb(1.1).setName("bar").setDate(Calendar.getInstance().getTime());
        System.out.println(EasyCSV.builder().setSource(foo).build());
    }
}