package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.dtos.CategoryDto;
import dev.vivek.productservicetutorial.dtos.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryService {
    CategoryDto[] getAllCategories();
    ProductDto[] getProductsInCategory (String categoryName);

}
