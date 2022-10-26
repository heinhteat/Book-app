package com.example.myapp.security;

import com.example.myapp.dao.CustomerDao;
import com.example.myapp.ds.SecurityCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDao
                .findCustomerByName(username)
                .map(SecurityCustomer::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not Found!"));
    }
}
