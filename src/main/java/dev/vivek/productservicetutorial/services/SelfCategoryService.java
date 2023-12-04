package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.dtos.CategoryDto;
import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.exceptions.NotFoundException;
import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import dev.vivek.productservicetutorial.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            categoryDtos.add(CategoryDto.to((category)));
        }
        return categoryDtos;
    }

    @Override
    public List<ProductDto> getProductsInCategory(String categoryName) {
        List<ProductDto> productDtos = new ArrayList<>();
        Optional<Category> category = categoryRepository.findByName(categoryName);
        if(!category.isPresent()){
            return null;
        }
        Set<Product> products = category.get().getProducts();
        for(Product product: products){
            productDtos.add(ProductDto.to(product));
        }
        return productDtos;
    }
}
