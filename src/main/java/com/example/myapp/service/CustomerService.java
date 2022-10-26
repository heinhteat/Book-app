package com.example.myapp.service;

import com.example.myapp.dao.CustomerBookOrderDao;
import com.example.myapp.dao.CustomerDao;
import com.example.myapp.dao.RolesDao;
import com.example.myapp.ds.BookDto;
import com.example.myapp.ds.Customer;
import com.example.myapp.ds.CustomerBookOrder;
import com.example.myapp.ds.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerBookOrderDao customerBookOrderDao;

    public Customer findCustomerByName(String name) {
        return customerDao.findCustomerByName(name).orElse(null);
    }

    @Transactional
    public void register(Customer customer, Set<BookDto> bookDtoList) {
        Roles roles = new Roles();
        roles.setRoleName("ROLE_USER");
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.addRoles(roles);

        for (BookDto bookDto : bookDtoList) {
            customer.addBook(cartService.toEntity(bookDto));
        }
        Customer customer1 = customerDao.saveAndFlush(customer);

        saveCustomerBookOrder(customer1, bookDtoList);
    }

    public void saveCustomerBookOrder(Customer customer1, Set<BookDto> bookDtoList) {
        CustomerBookOrder customerBookOrder = new CustomerBookOrder();
        customerBookOrder.setCustomer(customer1);
        customerBookOrder.setTotalAmount(totalPrices(bookDtoList));
        //customerBookOrder.setOrderCode(UUID.randomUUID().toString());
        customerBookOrder.setOrderCode(generateCode(customer1));
    }

    private double totalPrices(Set<BookDto> books) {
        return books
                .stream()
                .map(b -> b.getPrice() * b.getQuantity())
                .mapToDouble(d -> d)
                .sum();
    }

    private String generateCode(Customer customer) {
        Random random = new Random();
        int code = random.nextInt(100000) + 100000;
        return customer.getName() + code;
    }
}
