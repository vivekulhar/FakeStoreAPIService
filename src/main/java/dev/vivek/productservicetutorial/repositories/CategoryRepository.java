package dev.vivek.productservicetutorial.repositories;

import dev.vivek.productservicetutorial.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category save(Category category);
}
