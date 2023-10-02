package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
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
    public ProductDto addNewProduct(ProductDto productDto) {
        return restTemplate.postForObject("https://fakestoreapi.com/products", productDto, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductDto> requestEntity = new HttpEntity<>(productDto, headers);

        ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productId,
                HttpMethod.PUT,
                requestEntity,
                ProductDto.class
        );

        return responseEntity.getBody();
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
