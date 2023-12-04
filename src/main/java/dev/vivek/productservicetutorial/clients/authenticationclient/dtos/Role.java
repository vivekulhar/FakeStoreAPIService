package dev.vivek.productservicetutorial.clients.authenticationclient.dtos;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role {
    private String name;

    //private List<User> users
}
