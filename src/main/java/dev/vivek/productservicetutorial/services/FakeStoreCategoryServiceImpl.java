package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.dtos.CategoryDto;
import dev.vivek.productservicetutorial.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{
    RestTemplate restTemplate;
    public FakeStoreCategoryServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }
    @Override
    public CategoryDto[] getAllCategories() {
        return restTemplate.getForObject("https://fakestoreapi.com/products/categories", CategoryDto[].class);
        //return null;
    }

    @Override
    public ProductDto[] getProductsInCategory(String categoryName) {
        return restTemplate.getForObject("https://fakestoreapi.com/products/category/"+categoryName, ProductDto[].class);
    }
}
