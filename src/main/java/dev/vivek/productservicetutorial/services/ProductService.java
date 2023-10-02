package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

public interface ProductService {
    ProductDto[] getAllProducts();
    ProductDto getSingleProduct(Long productId);
    String addNewProduct(ProductDto productDto);
    String updateProduct(Long productId);
    String deleteProduct(Long productId);
}
