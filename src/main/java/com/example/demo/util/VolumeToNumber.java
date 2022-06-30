package com.example.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.demo.command.attribute.ProductAttribute.DIVISOR_BY_SINGLE_VALUE;

public class VolumeToNumber {

    public int convert(String volume) {
        volume = volume.substring(0, volume.length()-2);
        return Integer.parseInt(volume)/DIVISOR_BY_SINGLE_VALUE;
    }
}
