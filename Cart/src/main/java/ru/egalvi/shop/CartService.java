package ru.egalvi.shop;

/**
 */
public interface CartService {
    String checkout(Cart cart, ClientData clientData);

    Capture getCapture();
}
