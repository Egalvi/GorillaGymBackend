package kg.gorillagym.shop.cart;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.egalvi.shop.Capture;
import ru.egalvi.shop.CartService;

import static org.junit.Assert.*;

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
}