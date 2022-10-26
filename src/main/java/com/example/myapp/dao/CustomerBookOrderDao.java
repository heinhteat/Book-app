package com.example.myapp.dao;

import com.example.myapp.ds.CustomerBookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerBookOrderDao extends JpaRepository<CustomerBookOrder,Integer> {
}
