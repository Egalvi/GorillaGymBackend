package kg.gorillagym.shop.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.egalvi.shop.gorillagym.service.CategoryService;

import java.util.List;

@Category(IntegrationTest.class)
public class GorillaGymCategoryServiceIT {
    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        categoryService = new GorillaGymCategoryService();
    }

    @Test
    public void testGetAll() throws Exception {
        List<ru.egalvi.shop.gorillagym.model.Category> all = categoryService.getAll();
        for (ru.egalvi.shop.gorillagym.model.Category item : all) System.out.println(item);
    }
}
//HOWTO setup integration tests http://www.javacodegeeks.com/2015/01/separating-integration-tests-from-unit-tests-using-maven-failsafe-junit-category.html