package dev.vivek.productservicetutorial.clients.fakestoreapi;


import dev.vivek.productservicetutorial.dtos.RatingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements Serializable {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    private RatingDto rating;
}
