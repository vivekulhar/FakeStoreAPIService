package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.dtos.CategoryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryService {
    CategoryDto[] getAllCategories();
    String getProductsInCategory (Long categoryId);

}
