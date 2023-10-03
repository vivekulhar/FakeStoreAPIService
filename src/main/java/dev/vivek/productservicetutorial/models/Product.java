package dev.vivek.productservicetutorial.models;

import dev.vivek.productservicetutorial.dtos.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private Double price;
    private String description;
    private Category category;
    private String imageUrl;
    private Rating rating;
}
