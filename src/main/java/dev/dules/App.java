package dev.dules;

import java.util.ArrayList;
import java.util.Calendar;

public final class App {
    private App() {
    }

    public static void main(final String[] args) {
        final Foo foo = new Foo()
            .setId(1)
            .setName("bar")
            .setDate(Calendar.getInstance().getTime())
            .setList(new ArrayList<>());
        final EasyCSV csvBuilder = new EasyCSV(foo);
        System.out.println(csvBuilder.build());
    }
}
