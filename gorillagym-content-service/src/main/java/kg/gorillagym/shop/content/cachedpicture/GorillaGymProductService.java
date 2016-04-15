package kg.gorillagym.shop.content.cachedpicture;

import kg.gorillagym.shop.content.impl.RestClient;
import kg.gorillagym.shop.content.impl.RestClientFactory;
import org.apache.commons.io.IOUtils;
import retrofit.Call;
import ru.egalvi.shop.gorillagym.model.Category;
import ru.egalvi.shop.gorillagym.model.Product;
import ru.egalvi.shop.gorillagym.service.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static kg.gorillagym.shop.Constants.URL;

/**
 */
public class GorillaGymProductService implements ProductService {

    private final RestClient restClient;
    private Cache<byte[]> pictureCache;

    public GorillaGymProductService(Cache<byte[]> pictureCache) {
        //TODO move URL to settings
        restClient = RestClientFactory.create(URL);
        this.pictureCache = pictureCache;
    }

    public List<Product> getForCategory(Category category) {
        Call<List<Product>> products = restClient.getProductsForCategory(category.getId());
        try {
            List<Product> productList = products.execute().body();
            for (Product product : productList) {
                String imageUrl = product.getImage();
                byte[] cachedPicture = pictureCache.get(imageUrl);
                if (cachedPicture != null) {
                    product.setImageData(cachedPicture);
                } else {
                    InputStream in = null;
                    try {
                        URL url = new URL(imageUrl);
                        in = url.openStream();
                        byte[] bytes = IOUtils.toByteArray(in);
                        product.setImageData(bytes);
                        pictureCache.put(imageUrl, bytes);
                    } catch (IOException e) {
                        //TODO make own exception and handle it somewhere
//                    throw new RuntimeException(e);
                        product.setImageData(null);
                    } finally {
                        if (in != null) {
                            in.close();
                        }
                    }
                }
            }
            return productList;
        } catch (IOException e) {
            //TODO make own exception and handle it somewhere
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProduct(String id) {
        Call<List<Product>> products = restClient.getProductById(id);
        try {
            List<Product> productList = products.execute().body();
            for (Product product : productList) {
                String imageUrl = product.getImage();
                byte[] cachedPicture = pictureCache.get(imageUrl);
                if (cachedPicture != null) {
                    product.setImageData(cachedPicture);
                } else {
                    InputStream in = null;
                    try {
                        URL url = new URL(imageUrl);
                        in = url.openStream();
                        byte[] bytes = IOUtils.toByteArray(in);
                        product.setImageData(bytes);
                        pictureCache.put(imageUrl, bytes);
                    } catch (IOException e) {
                        //TODO make own exception and handle it somewhere
//                    throw new RuntimeException(e);
                        product.setImageData(null);
                    } finally {
                        if (in != null) {
                            in.close();
                        }
                    }
                }
            }
            return productList.isEmpty() ? null : productList.get(0);
        } catch (IOException e) {
            //TODO make own exception and handle it somewhere
            throw new RuntimeException(e);
        }
    }
}
