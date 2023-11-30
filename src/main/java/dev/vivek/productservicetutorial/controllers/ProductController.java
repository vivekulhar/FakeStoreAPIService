package dev.vivek.productservicetutorial.controllers;

import dev.vivek.productservicetutorial.dtos.*;
import dev.vivek.productservicetutorial.exceptions.NotFoundException;
import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import dev.vivek.productservicetutorial.repositories.ProductRepository;
import dev.vivek.productservicetutorial.services.ProductConverter;
import dev.vivek.productservicetutorial.services.ProductService;
import dev.vivek.productservicetutorial.services.SelfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ProductRepository productRepository;

    public ProductController(@Qualifier("selfProductService") ProductService productService, ProductRepository productRepository){
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @GetMapping()
    public List<ProductDto> getAllProducts(){
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for(Product product: products){
            productDtos.add(ProductConverter.toProductDto(product));
        }
        return productDtos;
    }
    /*@GetMapping("/{productId}")
    public GetSingleProductResponseDto getSingleProduct(@PathVariable Long productId){
        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
        responseDto.setProduct(productService.getSingleProduct(productId));

        return responseDto;
    }*/
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable Long productId) throws NotFoundException{


        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "no-access-for-you");

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if(productOptional.isEmpty()){
            throw new NotFoundException("No product with product id:"+productId+" found");

        }
        ProductDto productDto = ProductConverter.toProductDto(productOptional.get());
        ResponseEntity<ProductDto> response =new ResponseEntity(
                productDto,
                headers,
                HttpStatus.NOT_FOUND);

        return response;
    }
    @PostMapping("/addProduct")
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto){
//        Product newProduct = productService.addNewProduct(product);
//        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);

        Product product = productService.addNewProduct(productDto);
        ProductDto productDto1 = ProductConverter.toProductDto(product);
        ResponseEntity<ProductDto> response = new ResponseEntity<>(productDto1, HttpStatus.OK);
        return response;
    }
    @PatchMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
        Product product = ProductConverter.toProduct(productDto);

        product = productService.updateProduct(productId, product);
        ProductDto productDto1 = ProductConverter.toProductDto(product);
        return productDto1;
    }
    /*public Product createProductFromProductDto(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        Category category = product.getCategory();
        if (category == null) {
            category = new Category();
            product.setCategory(category);
        }
        product.setCategory((productDto.getCategory()));
        //product.setRating(productDto.getRating());
        return product;
    }*/
    @PutMapping("/{productId}")
    public ProductDto replaceProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
        Product product = ProductConverter.toProduct(productDto);

        product = productService.replaceProduct(productId, product);
        ProductDto productDto1 = ProductConverter.toProductDto(product);
        return productDto1;
    }
    @DeleteMapping("/{productId}")
    public Integer deleteProduct(@PathVariable Long productId){

        return productService.deleteProduct(productId);

    }

    /*@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(Exception exception){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(exception.getMessage());

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }*/
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
