package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.clients.fakestoreapi.FakeStoreClient;
import dev.vivek.productservicetutorial.clients.fakestoreapi.FakeStoreProductDto;
import dev.vivek.productservicetutorial.dtos.CategoryDto;
import dev.vivek.productservicetutorial.dtos.FakeStoreCategoryDto;
import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{
    RestTemplate restTemplate;
    FakeStoreClient fakeStoreClient;
    public FakeStoreCategoryServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient){
        this.restTemplate = restTemplateBuilder.build();
        this.fakeStoreClient = fakeStoreClient;
    }
    @Override
    public List<String> getAllCategories() {
        List<String> fakeStoreCategoryDto = fakeStoreClient.getAllCategories();


        return fakeStoreCategoryDto;

    }


    @Override
    public List<Product> getProductsInCategory(String categoryName) {
        return null;
    }
}
