package dev.vivek.productservicetutorial.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class BaseModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private boolean isDeleted;

}
