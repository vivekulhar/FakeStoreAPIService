package dev.vivek.productservicetutorial.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Rating {
    private double rate;
    private double count;

    // Getters and setters
}
