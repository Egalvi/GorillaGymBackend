package kg.gorillagym.shop.cart.impl;

import kg.gorillagym.shop.cart.GorillaGymClientData;
import ru.egalvi.shop.CartItem;
import ru.egalvi.shop.impl.CartImpl;

import java.util.Map;

/**
 */
public class CheckoutAdapter {

    // @formatter:off
    private static final String CHECKOUT_PATTERN =
            "Array ( " +
                    "[email] => %s " +
                    "[login] => %s " +
                    "[phone] => %s " +
                    "[address] => %s " +
                    "[imagestring] => %s " +
                    "[imagehash] => %s " +
            ")";

    private static final String ADDRESS_AND_CART_PATTERN = "Список товаров: %s Адрес, указанный покупателем: %s ";

    private static final String CART_ITEM_PATTERN = "%s - %.2f сом - %d шт. ";
    // @formatter:on

    public static String adapt(CartImpl cart, GorillaGymClientData clientData) {
        StringBuilder cartSb = new StringBuilder();
        for (Map.Entry<CartItem, Integer> cartItem : cart.getOrder().entrySet()) {
            cartSb.append(String.format(CART_ITEM_PATTERN, cartItem.getKey().getName(), cartItem.getKey().getPrice(), cartItem.getValue()));
        }
        String address = String.format(ADDRESS_AND_CART_PATTERN, cartSb.toString(), clientData.getAddress());
        return String.format(CHECKOUT_PATTERN
                , clientData.getEmail()
                , clientData.getName()
                , clientData.getPhone()
                , address
                , clientData.getCapture()
                , clientData.getToken());
    }
}
