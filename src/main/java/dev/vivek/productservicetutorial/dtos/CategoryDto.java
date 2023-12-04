package dev.vivek.productservicetutorial.dtos;

import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CategoryDto {

    private String name;
    private String description;
    private List<Product> products;
    /*public CategoryDto(String name) {
        this.name = name;
    }*/
    // Getters and setters
    public static Category from(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }

    public static CategoryDto to(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
