package dev.vivek.productservicetutorial.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class Category extends BaseModel{
    private String name;
    private String description;
    private List<Product> products;

}
