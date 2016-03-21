package ru.egalvi.shop;

/**
 */
public interface CartService {
    CheckoutResponse checkout(Cart cart, ClientData clientData);

    Capture getCapture();
}
