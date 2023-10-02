package dev.vivek.productservicetutorial.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class BaseModel {
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private boolean isDeleted;

}
