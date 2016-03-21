package kg.gorillagym.shop.content;

import static kg.gorillagym.shop.Constants.URL;
import static kg.gorillagym.shop.Constants.CATEGORY_BASE_URL;
import kg.gorillagym.shop.content.impl.RestClient;
import kg.gorillagym.shop.content.impl.RestClientFactory;
import org.apache.commons.io.IOUtils;
import retrofit.Call;
import ru.egalvi.shop.gorillagym.model.Category;
import ru.egalvi.shop.gorillagym.model.CategorySortComparator;
import ru.egalvi.shop.gorillagym.service.CategoryService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 */
public class GorillaGymCategoryService implements CategoryService {

    private RestClient restClient;

    public GorillaGymCategoryService() {
        //TODO move URL to settings
        restClient = RestClientFactory.create(URL);
    }

    // visible for testing
    void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Category> getAll() {
        Call<List<Category>> categories = restClient.getCategories();
        try {
            List<Category> result = categories.execute().body();
            Collections.sort(result, new CategorySortComparator());
            for (Category category : result) {
                String imgUrl = category.getImg();
                if(imgUrl != null && !imgUrl.equals("")) {
                    String url = CATEGORY_BASE_URL + "/" + category.getImg();
                    try (InputStream in = new URL(url).openStream()) {
                        byte[] bytes = IOUtils.toByteArray(in);
                        category.setImageData(bytes);
                    } catch (IOException e) {
                        //TODO make own exception and handle it somewhere
//                        throw new RuntimeException(e);
                        category.setImageData(null);
                    }
                }
            }
            return result;
        } catch (IOException e) {
            //TODO make own exception and handle it somewhere
            throw new RuntimeException(e);
        }
    }
}
