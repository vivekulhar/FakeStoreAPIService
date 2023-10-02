package dev.vivek.productservicetutorial.controllers;

import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.models.Product;
import dev.vivek.productservicetutorial.services.ProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping()
    public ProductDto[] getAllProducts(){

        return productService.getAllProducts();
    }
    @GetMapping("/{productId}")
    public ProductDto getSingleProduct(@PathVariable Long productId){
        return productService.getSingleProduct(productId);
    }
    @PostMapping()
    public ProductDto addNewProduct(@RequestBody ProductDto productDto){
        return productService.addNewProduct(productDto);
    }
    @PutMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
        return productService.updateProduct(productId, productDto);
    }
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        return "Deleting Product with id: "+productId;
    }
}
/*
@RestController
public class ProductController {
    @GetMapping("/products")
    public String getAllProducts(){
        return "Getting All Products";
    }
    @GetMapping("/products/{productId}")
    public String getSingleProduct(@PathVariable Long productId){
        return "Returning Single Product with id: "+productId;
    }
    @PostMapping("/products")
    public String addNewProduct(@RequestBody ProductDto productDto){
        return "Adding New Product " + productDto;
    }
    @PutMapping("/products/{productId}")
    public String updateProduct(@PathVariable Long productId){
        return "Updating Product with id: "+productId;
    }
    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        return "Deleting Product with id: "+productId;
    }
}*/
