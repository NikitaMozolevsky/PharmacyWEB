package by.mozolevskij.pharmacy.util;

public class AddressGenerator {
    public String addressGen(String country, String city, String street, String home) {
        return country + ", " + city + ", " + street + " " + home;
    }
}
