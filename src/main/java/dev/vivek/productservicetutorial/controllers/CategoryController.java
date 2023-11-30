package dev.vivek.productservicetutorial.controllers;

import dev.vivek.productservicetutorial.dtos.CategoryDto;
import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import dev.vivek.productservicetutorial.services.CategoryService;
import dev.vivek.productservicetutorial.services.SelfCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    private CategoryService categoryService;
    @Autowired
    public CategoryController(SelfCategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryDto> getAllCategories(){

        return categoryService.getAllCategories();
    }
    @GetMapping("/{categoryName}")
    public List<ProductDto> getProductsInCategory(@PathVariable("categoryName") String categoryName){
        return categoryService.getProductsInCategory(categoryName);
    }

}
/*
@RestController
public class CategoryController {
    @GetMapping("/products/categories")
    public String getAllCategories(){
        return "Getting All Categories";
    }
    @GetMapping("/products/categories/{categoryId}")
    public String getProductsInCategory(@PathVariable("categoryId") Long categoryId){
        return "Get Products in Category";
    }


}*/
