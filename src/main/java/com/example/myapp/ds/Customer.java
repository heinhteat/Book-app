package com.example.myapp.ds;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    //private String email;
    private String password;
    @Embedded
    private Address address;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    private List<Roles> roles = new ArrayList<>();

    @OneToMany
    public List<CustomerBookOrder> customerBookOrders = new ArrayList<>();

    @ManyToMany
    private List<Book> books = new ArrayList<>();

    public void addRoles(Roles role) {
        role.getCustomers().add(this);
        roles.add(role);
    }

    public void addOrder(CustomerBookOrder bookOrder){
        bookOrder.setCustomer(this);
        customerBookOrders.add(bookOrder);
    }

    public void addBook(Book book){
        book.getCustomers().add(this);
        books.add(book);
    }
}
