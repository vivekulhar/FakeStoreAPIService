package dev.vivek.productservicetutorial.repositories.product;

import dev.vivek.productservicetutorial.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    Product save(Product product);
    List<Product> findAllByIdIn(List<Long> id);
    Optional<Product> findProductById(Long id);
    Integer deleteProductById(Long id);

    Product findProductByIdIs(Long id);

    Product findProductByIdEquals(Long id);

    Product findByPriceBetweenAndTitleLessThanEqual(double greaterThanEqualPrice, double lessThanEqualPrice, String titleLessThan);
    // select * from products wherr


    Product findByPriceLessThanEqual(double price);
    // select * from products where prices <= 100

    // productRepository.findByPriceLessThanEqual(100);

    List<Product> findByImageUrlIsNullOrderByIdDesc();

    List<Product> findByTitleIgnoreCaseStartingWith(String title);
    // findByTitleLike("%" + title)
    // findByTitleStartingWith("naman")
    // findByTitleLike("%naman")

    //Naman => naman => NaMAn => namanN
    List<Product> findByTitleLikeIgnoreCase(String titleLike);
    //


    // productRepository.findByImageUrlIsNull()
    // select * from products where image_url is null;

    List<Product> findAllByIsPublic(boolean value);
    List<Product> findAllByIsPublicTrue();

    // productRepository.findAllByPublicIs(true);
    // productRepository.findAllByPublicIsTrue()

    Page<Product> findAll(Pageable pageable);
}
