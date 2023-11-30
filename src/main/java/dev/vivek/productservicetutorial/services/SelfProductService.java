package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.exceptions.NotFoundException;
import dev.vivek.productservicetutorial.models.Product;
import dev.vivek.productservicetutorial.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

public class SelfProductService implements ProductService{
    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        return Optional.empty();
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) {
        return null;
    }
    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }
}
