package dev.vivek.productservicetutorial.controllers;

import dev.vivek.productservicetutorial.clients.authenticationclient.AuthenticationClient;
import dev.vivek.productservicetutorial.dtos.*;
import dev.vivek.productservicetutorial.exceptions.NotFoundException;
import dev.vivek.productservicetutorial.models.Product;
import dev.vivek.productservicetutorial.repositories.product.ProductRepository;
import dev.vivek.productservicetutorial.services.ProductConverter;
import dev.vivek.productservicetutorial.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ProductRepository productRepository;
    private AuthenticationClient authenticationClient;
    public ProductController(@Qualifier("selfProductService") ProductService productService,
                             ProductRepository productRepository,
                             AuthenticationClient authenticationClient){
        this.productService = productService;
        this.productRepository = productRepository;
        this.authenticationClient = authenticationClient;
    }
    @GetMapping("/paginated")
    public ResponseEntity<Page<Product>> getProducts(@RequestBody GetProductsRequestDto getProductsRequestDto){


        Page<Product> products = productService.getProducts(getProductsRequestDto.getNumberOfResults(), getProductsRequestDto.getOffset());

        return new ResponseEntity<>(products, HttpStatus.OK);
//        return null;
    }

    // Make only admins to be able to access this endpoint
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        // check if token and userId are present

        // validate the token

        // check if token is valid


        // validate token
        // RestTemplate rt = new RestTemplate();
        //  rt.get("localhost:9090/auth/validate?)

        // check if user has permissions

        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for(Product product: products){
            productDtos.add(ProductConverter.toProductDto(product));
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
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
        headers.add("auth-token", "noaccessforyou");

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if(productOptional.isEmpty()){
            throw new NotFoundException("No product with product id:"+productId+" found");

        }
        ProductDto productDto = ProductConverter.toProductDto(productOptional.get());
        ResponseEntity<ProductDto> response =new ResponseEntity(
                productDto,
                headers,
                HttpStatus.OK);

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
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
        Product product = ProductConverter.toProduct(productDto);

        product = productService.updateProduct(productId, product);
        ProductDto productDto1 = ProductConverter.toProductDto(product);
        return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
        Product product = ProductConverter.toProduct(productDto);

        product = productService.replaceProduct(productId, product);
        ProductDto productDto1 = ProductConverter.toProductDto(product);
        return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }
    @DeleteMapping("/{productId}")
    public Integer deleteProduct(@PathVariable Long productId){

        return productService.deleteProduct(productId);

    }


}

