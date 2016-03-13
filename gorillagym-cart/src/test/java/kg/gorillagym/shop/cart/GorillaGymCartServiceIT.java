package kg.gorillagym.shop.cart;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.egalvi.shop.Capture;
import ru.egalvi.shop.CartItem;
import ru.egalvi.shop.CartService;
import ru.egalvi.shop.ClientData;
import ru.egalvi.shop.impl.CartImpl;

@Category(IntegrationTest.class)
public class GorillaGymCartServiceIT {

    private CartService cartService;

    @Before
    public void setUp() throws Exception {
        cartService = new GorillaGymCartService();
    }

    @Test
    public void testGetCapture() throws Exception {
        Capture capture = cartService.getCapture();
        System.out.println(capture);
    }

    @Test
    public void testCheckout() throws Exception {
        CartImpl cart = new CartImpl();
        cart.add(new CartItem() {
            @Override
            public String getId() {
                return "id";
            }

            @Override
            public String getName() {
                return "Product name";
            }

            @Override
            public double getPrice() {
                return 4500;
            }
        }, 5);
        String checkout = cartService.checkout(cart, new GorillaGymClientData("email", "name", "phone", "address", "token", "capture"));
        System.out.print(checkout);
    }
}