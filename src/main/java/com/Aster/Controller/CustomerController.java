package com.Aster.Controller;
import com.Aster.Model.Customer;
import com.Aster.Model.Order;
import com.Aster.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    //private OrderService orderService;
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public int addCustomer(@RequestBody Customer customer) throws Exception {
        System.out.println("controller layer");
        return customerService.addCustomer(customer);
    }

    @GetMapping("/get")
    public String getCustomername(@RequestParam String email) throws Exception{
        System.out.println("getting username");
        return customerService.getCustomername(email);
    }

    @DeleteMapping("/delete")
    public int deleteCustomer(@RequestParam String email) throws Exception{
        System.out.println("deleting user with email: " + email);
        return customerService.deleteCustomer(email);
    }

    /*@PostMapping("/cart/add")
    public int addCart(@RequestBody Order order, @RequestParam String email) throws Exception{
        System.out.println("adding to cart");
        return customerService.addCart(email, order);
    }*/




}
