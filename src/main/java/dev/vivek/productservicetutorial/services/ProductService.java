package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

public interface ProductService {
    ProductDto[] getAllProducts();
    ProductDto getSingleProduct(Long productId);
    ProductDto addNewProduct(ProductDto productDto);
    ProductDto updateProduct(Long productId, ProductDto productDto);
    String deleteProduct(Long productId);
}
