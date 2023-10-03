package dev.vivek.productservicetutorial.clients.fakestoreapi;

import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
        this.restTemplate = restTemplateBuilder.build();
    }
    public List<FakeStoreProductDto> getAllProducts(){
//        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        return List.of(l.getBody());
    }
    Optional<FakeStoreProductDto> getSingleProduct(Long productId){

        return null;
    }
    FakeStoreProductDto addNewProduct(ProductDto product){
        return null;
    }
    FakeStoreProductDto updateProduct(Long productId, Product product){
        return null;
    }
    FakeStoreProductDto replaceProduct(Long productId, Product product){
        return null;
    }
    FakeStoreProductDto deleteProduct(Long productId){
        return null;
    }
}
