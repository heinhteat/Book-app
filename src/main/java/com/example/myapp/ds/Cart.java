package com.example.myapp.ds;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@SessionScope
public class Cart {

    private Set<BookDto> bookDtos=new HashSet<>();

    public int cartSize(){
        return bookDtos.size();
    }

    public void addToCart(BookDto bookDto){
        bookDtos.add(bookDto);
    }

    public Set<BookDto> getBookDtos() {
        return bookDtos;
    }

    public void removeBookFromCart(BookDto bookDto){
        bookDtos.remove(bookDto);
    }

    public void clearCart(){
        bookDtos.clear();
    }
}
