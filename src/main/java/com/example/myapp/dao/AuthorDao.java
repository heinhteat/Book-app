package com.example.myapp.dao;

import com.example.myapp.ds.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author,Integer> {
}
