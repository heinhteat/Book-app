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
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Embedded
    private Address address = new Address();
    @OneToMany(mappedBy = "author")
    private List<Book> bookList = new ArrayList<>();
    public void addBook(Book book){
        book.setAuthor(this);
        bookList.add(book);
    }
}
