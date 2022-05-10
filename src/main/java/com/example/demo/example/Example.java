package com.example.demo.example;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Example {
    String string = "qwe";

    public static void main(String[] args) {
         Logger logger = LogManager.getLogger();
         logger.log(Level.INFO, "qwertyu");
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Example example = (Example) o;

        return string != null ? string.equals(example.string) : example.string == null;
    }

    @Override
    public int hashCode() {
        return string != null ? string.hashCode() : 0;
    }
}
