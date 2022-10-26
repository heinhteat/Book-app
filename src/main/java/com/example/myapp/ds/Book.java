package com.example.myapp.ds;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearPublished;
    private String description;
    private String imgUrl;
    @CollectionTable(name = "comments")
    @ElementCollection
    private List<String> comments = new ArrayList<>();

    @ManyToOne
    private Category category;
    @ManyToOne
    private Author author;

    @Transient
    private Integer categoryId;
    @Transient
    private Integer authorId;

    @ManyToMany(mappedBy = "books")
    private List<Customer> customers = new ArrayList<>();
}
