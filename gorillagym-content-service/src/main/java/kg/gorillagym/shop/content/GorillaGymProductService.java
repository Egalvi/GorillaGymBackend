package kg.gorillagym.shop.content;

import static kg.gorillagym.shop.Constants.URL;
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

/**
 */
public class GorillaGymProductService implements ProductService {

    private final RestClient restClient;

    public GorillaGymProductService() {
        //TODO move URL to settings
        restClient = RestClientFactory.create(URL);
    }

    public List<Product> getForCategory(Category category) {
        Call<List<Product>> products = restClient.getProductsForCategory(category.getId());
        try {
            List<Product> productList = products.execute().body();
            for (Product product : productList) {
                InputStream in = null;
                try {
                    URL url = new URL(product.getImage());
                    in = url.openStream();
                    byte[] bytes = IOUtils.toByteArray(in);
                    product.setImageData(bytes);
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
            return productList;
        } catch (IOException e) {
            //TODO make own exception and handle it somewhere
            throw new RuntimeException(e);
        }
    }
}
