package dev.vivek.productservicetutorial.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@Entity
@ToString
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",cascade = {CascadeType.REMOVE})
    private List<Product> products;
}
