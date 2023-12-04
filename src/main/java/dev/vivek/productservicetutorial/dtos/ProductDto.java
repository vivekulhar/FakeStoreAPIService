package dev.vivek.productservicetutorial.dtos;

import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;


    public static Product from(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }

    public static ProductDto to(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }
}
