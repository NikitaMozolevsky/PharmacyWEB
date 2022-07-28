package by.mozolevskij.pharmacy.util;

import by.mozolevskij.pharmacy.command.attribute.ProductAttribute;

public class VolumeToNumber {

    static final int INITIAL_VALUE = 0;
    static final int FRACTIONAL_PART = 2;

    public int convert(String volume) {
        volume = volume.substring(INITIAL_VALUE, volume.length()-FRACTIONAL_PART);
        return Integer.parseInt(volume)/ ProductAttribute.DIVISOR_BY_SINGLE_VALUE;
    }
}
