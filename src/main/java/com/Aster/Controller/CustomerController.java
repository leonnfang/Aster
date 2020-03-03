package com.Aster.Controller;
import com.Aster.Model.Customer;
import com.Aster.Model.Order;
import com.Aster.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public int addCustomer(@RequestBody Customer customer){
        System.out.println("in controller!!!!");
        System.out.println(customer.getEmail());
        return customerService.addCustomer(customer);
    }
    @GetMapping("/get")
    public String getCustomername(@RequestParam String email) throws Exception{
        System.out.println("getting username");
        return customerService.getCustomername(email);
    }
    @DeleteMapping("/{email}/delete")
    public int deleteCustomer(@PathVariable String email) throws Exception{
        System.out.println("deleting user with email: " + email);
        return customerService.deleteCustomer(email);
    }

    @PostMapping("/{email}/cart/add")
    public int addCart(@PathVariable String email, @RequestBody Order order) throws Exception{
        System.out.println("adding to cart");
        return customerService.addCart(email, order);
    }
    @GetMapping("/{email}/cart/view")
    @ResponseBody
    public List<Order> viewCart(@PathVariable String email) throws Exception{
        return customerService.viewCart(email);
    }
    @DeleteMapping("/{email}/cart/empty")
    public int emptyCart(@PathVariable String email) throws Exception{
        System.out.println("emptying cart");
        return customerService.emptyCart(email);
    }
    @DeleteMapping("/{email}/cart/remove")
    public int removeCart(@PathVariable String email, @RequestBody Order order) throws Exception{
        return customerService.removeCart(email, order);
    }
}
