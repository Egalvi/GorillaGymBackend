package ru.egalvi.shop;

/**
 */
public interface Capture {
    public String getToken();

    public String getImagehash();

    public byte[] getImagedata();
}
