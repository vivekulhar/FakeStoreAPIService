package dev.vivek.productservicetutorial.repositories;

import dev.vivek.productservicetutorial.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

//    Category save(Category category);
    Optional<List<Category>> findAllByIdIn(List<Long> ids);
    Category findCategoryById(Long id);

    List<Category> findAll();

    Optional<Category> findByName(String categoryName);
}
