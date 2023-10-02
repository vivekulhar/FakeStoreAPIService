package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    RestTemplate restTemplate;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }
    @Override
    public ProductDto[] getAllProducts() {
        return restTemplate.getForObject("https://fakestoreapi.com/products", ProductDto[].class);
    }

    @Override
    public ProductDto getSingleProduct(Long productId) {

        return restTemplate.getForObject("https://fakestoreapi.com/products/"+productId, ProductDto.class);
    }

    @Override
    public String addNewProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public String updateProduct(Long productId) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
