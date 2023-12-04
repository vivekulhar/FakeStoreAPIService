package dev.vivek.productservicetutorial.controllers;

import dev.vivek.productservicetutorial.dtos.CategoryDto;
import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import dev.vivek.productservicetutorial.services.CategoryService;
import dev.vivek.productservicetutorial.services.SelfCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    private CategoryService categoryService;
    @Value("${hello.message}")
    private String helloWorld;
    @Autowired
    public CategoryController(@Qualifier("selfCategoryService") CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategories(){

        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }
    @GetMapping("/{categoryName}")
    public ResponseEntity<List<ProductDto>> getProductsInCategory(@PathVariable("categoryName") String categoryName){
        List<ProductDto> productDtos = categoryService.getProductsInCategory(categoryName);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
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
