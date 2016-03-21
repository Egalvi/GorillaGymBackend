package kg.gorillagym.shop.cart.impl;

import static kg.gorillagym.shop.Constants.CAPTURE;
import static kg.gorillagym.shop.Constants.SUBMIT_ORDER;

import kg.gorillagym.shop.cart.CaptureImpl;
import retrofit.Call;
import retrofit.http.*;
import ru.egalvi.shop.CheckoutResponse;

/**
 */
public interface RestClient {
    @FormUrlEncoded
    @POST(SUBMIT_ORDER)
    Call<CheckoutResponse> submitOrder(@Field("email") String email
                                , @Field("login") String login
                                , @Field("phone") String phone
                                , @Field("address") String address
                                , @Field("cart_coast") String totalCost
                                , @Field("imagestring") String imagestring
                                , @Field("imagehash") String imagehash);

    @GET(CAPTURE)
    Call<CaptureImpl> getCapture();
}
