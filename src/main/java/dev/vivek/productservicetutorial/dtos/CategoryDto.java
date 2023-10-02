package dev.vivek.productservicetutorial.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDto {
    private Long id;
    private String name;
    public CategoryDto(String name) {
        this.name = name;
    }
    // Getters and setters
}
