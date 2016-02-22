package kg.gorillagym.shop.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.egalvi.shop.gorillagym.model.Product;
import ru.egalvi.shop.gorillagym.service.CategoryService;
import ru.egalvi.shop.gorillagym.service.ProductService;

import java.util.List;

@Category(IntegrationTest.class)
public class GorillaGymProductServiceIT {

    CategoryService categoryService;

    ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new GorillaGymProductService();
        categoryService = new GorillaGymCategoryService();
    }

    @Test
    public void testGetAll() throws Exception {
        List<Product> products = productService.getForCategory(categoryService.getAll().get(0));
        System.out.println(products);
    }
}
