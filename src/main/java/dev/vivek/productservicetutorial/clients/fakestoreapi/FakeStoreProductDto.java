package dev.vivek.productservicetutorial.clients.fakestoreapi;

import dev.vivek.productservicetutorial.dtos.Rating;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    private Rating rating;
}
