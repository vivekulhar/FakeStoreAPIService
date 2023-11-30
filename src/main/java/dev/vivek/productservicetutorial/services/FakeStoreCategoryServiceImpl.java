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
    public List<CategoryDto> getAllCategories() {
        List<Category> fakeStoreCategoryDto = fakeStoreClient.getAllCategories();

        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category category: fakeStoreCategoryDto){
            categoryDtos.add(CategoryConverter.toCategoryDto
                    (category));
        }
        return categoryDtos;

    }


    @Override
    public List<ProductDto> getProductsInCategory(String categoryName) {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getProductsInCategory(categoryName);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto productDto: fakeStoreProductDtos){
            products.add(ProductConverter.convertFakeStoreProductDtoToProduct(productDto));
        }
        List<ProductDto> productDtos = new ArrayList<>();
        return productDtos;
    }
    /*private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setRating(productDto.getRating());
        return product;
    }*/
}
