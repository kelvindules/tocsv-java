package dev.dules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Foo foo = new Foo();
        logger.info(ObjectUtil.buildHeader(foo));
    }
}
