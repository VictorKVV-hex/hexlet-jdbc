package org.example.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {

    private Long id;
    private String name;
    private String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
