package org.example.userdao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String Phone;

    public User(String name, String description) {
        this.name = name;
        this.Phone = description;
    }
}
