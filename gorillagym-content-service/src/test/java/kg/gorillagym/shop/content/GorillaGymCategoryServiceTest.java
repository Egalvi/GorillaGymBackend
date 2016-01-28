package kg.gorillagym.shop.content;

import kg.gorillagym.shop.content.impl.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.http.Query;
import ru.egalvi.shop.gorillagym.model.CategorySortComparator;
import ru.egalvi.shop.gorillagym.model.Product;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.egalvi.shop.gorillagym.model.Category;
import ru.egalvi.shop.gorillagym.service.CategoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 */
public class GorillaGymCategoryServiceTest {

    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        categoryService = new GorillaGymCategoryService();
    }

    @Test
//    @Ignore("This is integration test")
    public void testGetAll() throws Exception {
        List<Category> all = categoryService.getAll();
        for (Category item : all) System.out.println(item);
    }

    @Test
    @Ignore
    public void testGetAllSorted() throws Exception {
        ((GorillaGymCategoryService)categoryService)
                .setRestClient(new MockRestClient());
        List<Category> categories = categoryService.getAll();
        for (Category item : categories) System.out.println(item);
        assertListSorted(categories);
    }

    private boolean isFirstLessThanSecond(Category first, Category second) {
        return new CategorySortComparator().compare(first, second)< 0;
    }

    private void assertListSorted(List<Category> list) {
        for (int i = 0; i < list.size() - 1; i++)
            assertTrue(isFirstLessThanSecond(list.get(i), list.get(i + 1)));
    }

    private class MockRestClient implements RestClient {

        public Call<List<Category>> getCategories() {
            return new Call<List<Category>>() {
                public Response<List<Category>> execute() throws IOException {
                    List<Category>categories = new ArrayList<Category>();
                    categories.add(new Category("1", "Foo", "descr 1", 4));
                    categories.add(new Category("2", "Bar", "descr 1", 3));
                    categories.add(new Category("3", "Baz", "descr 1", 2));
                    categories.add(new Category("4", "FooBar", "descr 1", 1));
                    return Response.success(categories);
                }

                public void enqueue(Callback<List<Category>> callback) {

                }

                public void cancel() {

                }

                public Call<List<Category>> clone() {
                    return null;
                }
            };
        }

        public Call<List<Product>> getProductsForCategory(@Query("category") String categoryId) {
            return null;
        }
    }
}