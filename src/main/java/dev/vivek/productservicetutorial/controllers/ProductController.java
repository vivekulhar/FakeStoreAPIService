package dev.vivek.productservicetutorial.controllers;

import dev.vivek.productservicetutorial.dtos.*;
import dev.vivek.productservicetutorial.exceptions.NotFoundException;
import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import dev.vivek.productservicetutorial.services.ProductService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping()
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }
    /*@GetMapping("/{productId}")
    public GetSingleProductResponseDto getSingleProduct(@PathVariable Long productId){
        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
        responseDto.setProduct(productService.getSingleProduct(productId));

        return responseDto;
    }*/
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Long productId) throws NotFoundException{


        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "no-access-for-you");

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if(productOptional.isEmpty()){
            throw new NotFoundException("No product with product id:"+productId+" found");

        }
        ResponseEntity<Product> response =new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND);

        return response;
    }
    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product){
        Product newProduct = productService.addNewProduct(product);
        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);
        return response;
    }
    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
        Product product = new Product();
        product.setId(productId);
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImage());
        Category category = product.getCategory();
        if (category == null) {
            category = new Category();
            product.setCategory(category);
        }
        product.getCategory().setName(productDto.getCategory());
        product.setRating(productDto.getRating());

        return productService.updateProduct(productId, product);
    }
    @DeleteMapping("/{productId}")
    public boolean deleteProduct(@PathVariable Long productId){
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
