package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.dtos.CategoryDto;
import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import dev.vivek.productservicetutorial.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SelfCategoryService implements CategoryService{

    CategoryRepository categoryRepository;
    public SelfCategoryService(CategoryRepository categoryRepository){

        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category category: categories){
            categoryDtos.add(CategoryConverter.toCategoryDto(category));
        }
        return categoryDtos;
    }

    @Override
    public List<ProductDto> getProductsInCategory(String categoryName) {
        List<ProductDto> productDtos = new ArrayList<>();
        Category category = categoryRepository.findByName(categoryName);
        Set<Product> products = category.getProducts();
        for(Product product: products){
            productDtos.add(ProductConverter.toProductDto(product));
        }
        return productDtos;
    }
}
