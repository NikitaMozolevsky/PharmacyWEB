package by.mozolevskij.pharmacy.example;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StreamExample {
    String string = "qwe";

    public static void main(String[] args) {
         Logger logger = LogManager.getLogger();
         logger.log(Level.INFO, "qwertyu");
    }

    public class Example1 {
        public static void main(String[] args) {
            new Thread(
                    () -> System.out.println("hello world")
            ).start();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StreamExample streamExample = (StreamExample) o;

        return string != null ? string.equals(streamExample.string) : streamExample.string == null;
    }

    @Override
    public int hashCode() {
        return string != null ? string.hashCode() : 0;
    }
}
