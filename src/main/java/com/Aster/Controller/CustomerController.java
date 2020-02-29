package com.Aster.Controller;
import com.Aster.Model.Customer;
import com.Aster.Model.User;
import com.Aster.Service.CustomerService;
import com.Aster.Service.OrderService;
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

    @PostMapping("/add_customer")
    public int addCustomer(@RequestBody Customer customer) throws Exception {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/get_customername")
    public String getCustomername(@RequestParam String email) throws Exception{
        System.out.println("getting username");
        return customerService.getCustomername(email);
    }

    @DeleteMapping("/delete_customer")
    public int deleteCustomer(@RequestParam String email) throws Exception{
        System.out.println("deleting user with email: " + email);
        return customerService.deleteCustomer(email);
    }


}
