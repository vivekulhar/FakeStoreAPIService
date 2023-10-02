package dev.vivek.productservicetutorial;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.vivek.productservicetutorial.controllers.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(String type, Value value) {
}
