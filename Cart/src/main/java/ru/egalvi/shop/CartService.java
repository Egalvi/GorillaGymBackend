package ru.egalvi.shop;

/**
 */
public interface CartService {
    void checkout(Cart cart, ClientData clientData);

    Capture getCapture();
}
