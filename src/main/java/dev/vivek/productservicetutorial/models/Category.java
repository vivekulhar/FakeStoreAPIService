package dev.vivek.productservicetutorial.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
public class Category extends BaseModel{
    @Column(unique = true)
    private String name;
    private String description;
    /*@OneToMany(mappedBy = "category",cascade = {CascadeType.REMOVE})
    private List<Product> products;*/
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "category", cascade =
            {CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    @BatchSize(size=1)
    private Set<Product> products;

    // FetchModes allow to solve how a associated object is fetched
// Spring Data JPA modifies the behaviour of FetchModes a lot
}
