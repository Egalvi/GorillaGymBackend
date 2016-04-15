package ru.egalvi.shop.service.impl;

import ru.egalvi.shop.gorillagym.model.Category;
import ru.egalvi.shop.gorillagym.model.Product;
import ru.egalvi.shop.gorillagym.service.CategoryService;
import ru.egalvi.shop.gorillagym.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class TestProductService implements ProductService {
    private Map<Category, List<Product>> categoryProductMap;

    public TestProductService() {
        categoryProductMap = new HashMap<Category, List<Product>>();
        CategoryService categoryService = new TestCategoryService();
        for (Category category : categoryService.getAll()){
            List<Product> products = new ArrayList<Product>();
            products.add(new Product("1", "Prod 1 of " + category.getName(), "descr 1", 10.0, null));
            products.add(new Product("2", "Prod 2 of " + category.getName(), "descr 2", 10.0, null));
            products.add(new Product("3", "Prod 3 of " + category.getName(), "descr 3", 10.0, null));
            categoryProductMap.put(category, products);
        }
    }

    public List<Product> getForCategory(Category category) {
        return categoryProductMap.get(category);
    }

    @Override
    public Product getProduct(String id) {
        return null;
    }
}
