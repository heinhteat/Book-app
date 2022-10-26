package com.example.myapp.controller;

import com.example.myapp.dao.CustomerDao;
import com.example.myapp.ds.BookDto;
import com.example.myapp.ds.Customer;
import com.example.myapp.service.CartService;
import com.example.myapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }

    private List<Integer> bookQuantityList;

    @RequestMapping("/customer/register")
    public String register(Model model, BookDto bookDto) {
        this.bookQuantityList = bookDto.getBookNumberList();
        System.out.println(bookQuantityList);
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/customer/save-customer")
    public String saveCustomer(Customer customer, BindingResult bindingResult) {
        Set<BookDto> bookDtoSet = cartService.listCart();
        int index = 0;
        for (BookDto bookDto : bookDtoSet) {
            bookDto.setQuantity(this.bookQuantityList.get(index));
            index++;
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }
        Customer existingCustomer = customerService.findCustomerByName(customer.getName());
        if (existingCustomer == null) {
            customerService.register(customer, bookDtoSet);
        } else {
            customerService.saveCustomerBookOrder(existingCustomer, bookDtoSet);
        }
        cartService.clearCart();
        return "redirect:/login";
    }
}
