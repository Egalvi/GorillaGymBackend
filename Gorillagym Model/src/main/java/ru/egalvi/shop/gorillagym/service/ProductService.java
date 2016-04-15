package ru.egalvi.shop.gorillagym.service;

import ru.egalvi.shop.gorillagym.model.Category;
import ru.egalvi.shop.gorillagym.model.Product;

import java.util.List;

/**
 */
public interface ProductService {
    List<Product> getForCategory(Category category);

    Product getProduct(String id);
}
