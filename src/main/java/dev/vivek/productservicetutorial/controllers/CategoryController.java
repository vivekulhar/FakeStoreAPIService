package dev.vivek.productservicetutorial.controllers;

import dev.vivek.productservicetutorial.dtos.CategoryDto;
import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping()
    public CategoryDto[] getAllCategories(){

        return categoryService.getAllCategories();
    }
    @GetMapping("/{categoryName}")
    public ProductDto[] getProductsInCategory(@PathVariable("categoryName") String categoryName){
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
