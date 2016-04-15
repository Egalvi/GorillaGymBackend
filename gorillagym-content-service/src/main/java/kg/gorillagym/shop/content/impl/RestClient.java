package kg.gorillagym.shop.content.impl;

import static kg.gorillagym.shop.Constants.CATEGORIES;
import static kg.gorillagym.shop.Constants.PRODUCTS;
import static kg.gorillagym.shop.Constants.PRODUCT;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import ru.egalvi.shop.gorillagym.model.Category;
import ru.egalvi.shop.gorillagym.model.Product;

import java.util.List;

/**
 */
public interface RestClient {

    @GET(CATEGORIES)
    Call<List<Category>> getCategories();

    @GET(PRODUCTS)
    Call<List<Product>> getProductsForCategory(@Query("category") String categoryId);

    @GET(PRODUCT)
    Call<List<Product>> getProductById(@Query("id") String productId);
}
