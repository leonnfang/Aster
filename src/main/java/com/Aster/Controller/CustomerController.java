package com.Aster.Controller;
import com.Aster.Model.Customer;
import com.Aster.Model.Order;
import com.Aster.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    //private OrderService orderService;
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public int addCustomer(@RequestBody Customer customer) throws Exception{
        return customerService.addCustomer(customer);
    }
    @GetMapping("/get")
    public String getCustomername(@RequestParam String email) throws Exception{
        return customerService.getCustomername(email);
    }
    @DeleteMapping("/{email}/delete")
    public int deleteCustomer(@PathVariable String email) throws Exception{
        return customerService.deleteCustomer(email);
    }


    @PostMapping("/{email}/cart/add")
    public int addCart(@PathVariable String email, @RequestBody Order order) throws Exception{
        return customerService.addCart(email, order);
    }
    @ResponseBody
    @GetMapping("/{email}/cart/view")
    public List<Order> viewCart(@PathVariable String email) throws Exception{
        return customerService.viewCart(email);
    }
    @DeleteMapping("/{email}/cart/empty")
    public int emptyCart(@PathVariable String email) throws Exception{
        return customerService.emptyCart(email);
    }
    @DeleteMapping("/{email}/cart/remove")
    public int removeCart(@PathVariable String email, @RequestParam String orderID) throws Exception{
        return customerService.removeCart(email, orderID);
    }
}
