package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.clients.fakestoreapi.FakeStoreProductDto;
import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;

public class ProductConverter {
    public static Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());

        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);

        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setRating(productDto.getRating());

        return product;
    }

    public static FakeStoreProductDto convertProductToFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setRating(product.getRating());

        return fakeStoreProductDto;
    }
}