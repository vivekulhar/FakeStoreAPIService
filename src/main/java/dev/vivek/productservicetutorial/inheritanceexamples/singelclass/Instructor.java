package dev.vivek.productservicetutorial.inheritanceexamples.singelclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_instructor")
@DiscriminatorValue("3")
public class Instructor extends User {
    private boolean isHandsome;
}
