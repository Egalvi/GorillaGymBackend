package kg.gorillagym.shop.cart;

import kg.gorillagym.shop.cart.impl.CheckoutAdapter;
import ru.egalvi.shop.CheckoutResponse;
import kg.gorillagym.shop.cart.impl.RestClient;
import kg.gorillagym.shop.cart.impl.RestClientFactory;
import org.apache.commons.io.IOUtils;
import retrofit.Call;
import ru.egalvi.shop.Cart;
import ru.egalvi.shop.CartItem;
import ru.egalvi.shop.CartService;
import ru.egalvi.shop.ClientData;
import ru.egalvi.shop.impl.CartImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static kg.gorillagym.shop.Constants.URL;

/**
 */
public class GorillaGymCartService implements CartService {
    private final RestClient restClient;

    public GorillaGymCartService() {
        //TODO move URL to settings
        restClient = RestClientFactory.create(URL);
    }

    public CheckoutResponse checkout(Cart cart, ClientData clientData) {
        List<OrderItem> orderItems = new ArrayList<OrderItem>(cart.getOrder().size());
        for (Map.Entry<CartItem, Integer> cartItem : cart.getOrder().entrySet()) {
            if (cartItem.getValue() != null) {
                orderItems.add(new OrderItem(cartItem.getKey().getId(), cartItem.getValue()));
            }
        }
        GorillaGymClientData cd = (GorillaGymClientData) clientData;
        Call<CheckoutResponse> stringCall = restClient.submitOrder(cd.getEmail()
                , cd.getName()
                , cd.getPhone()
                , CheckoutAdapter.getAddressWithOrder((CartImpl) cart, cd)
                , Double.toString(cart.getTotalPrice())
                , cd.getCapture()
                , cd.getToken());
        try {
            return stringCall.execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CaptureImpl getCapture() {
        Call<CaptureImpl> captureCall = restClient.getCapture();
        try {
            CaptureImpl capture = captureCall.execute().body();
            String imgUrl = capture.getImagehash();
            if (imgUrl != null && !imgUrl.equals("")) {
                try (InputStream in = new java.net.URL(imgUrl).openStream()) {
                    byte[] bytes = IOUtils.toByteArray(in);
                    capture.setImagedata(bytes);
                } catch (IOException e) {
                    //TODO make own exception and handle it somewhere
                    throw new RuntimeException(e);
                }
            }
            return capture;
        } catch (IOException e) {
            //TODO make own exception and handle it somewhere
            throw new RuntimeException(e);
        }
    }
}
