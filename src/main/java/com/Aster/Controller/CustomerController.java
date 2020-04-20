package com.Aster.Controller;
import com.Aster.Model.Customer;
import com.Aster.Model.Purchase;
import com.Aster.Service.CustomerService;
import com.Aster.Service.JpaCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    //private OrderService orderService;
    private CustomerService customerService;
    private JpaCustomerService jpaCustomerService;

    @Autowired
    public CustomerController(CustomerService customerService, JpaCustomerService jpaCustomerService) {
        this.customerService = customerService;
        this.jpaCustomerService = jpaCustomerService;
    }

    @PostMapping("/add")
    public boolean addCustomer(@RequestBody Customer customer) throws Exception{
        return jpaCustomerService.addCustomer(customer);
    }
    @ResponseBody
    @GetMapping("/get")
    public Customer getCustomer(@RequestParam String email) throws Exception{
        return jpaCustomerService.getCustomer(email);
    }
    @DeleteMapping("/{email}/delete")
    public boolean deleteCustomer(@PathVariable String email) throws Exception{
        return jpaCustomerService.deleteCustomer(email);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public List<String> viewCustomers(){
        return jpaCustomerService.viewCustomers();
    }


    @PostMapping("/{email}/cart/add")
    public boolean addCart(@PathVariable String email,
                           @RequestBody Purchase purchase) throws Exception{
        return customerService.addCart(email, purchase);
    }
    @DeleteMapping("/{email}/cart/remove")
    public boolean removeCart(@PathVariable String email,
                              @RequestParam String orderID) throws Exception{
        return customerService.removeCart(email, orderID);
    }
    @ResponseBody
    @GetMapping("/{email}/cart/view")
    public List<Purchase> viewCart(@PathVariable String email) throws Exception{
        return customerService.viewCart(email);
    }
    @DeleteMapping("/{email}/cart/empty")
    public boolean emptyCart(@PathVariable String email) throws Exception{
        return customerService.emptyCart(email);
    }


    @PutMapping("/{email}/checkout")
    public boolean checkout(@PathVariable String email) throws Exception {
        return customerService.checkout(email);
    }
    @ResponseBody
    @GetMapping("{email}/history/view")
    public List<Purchase> viewHistory(@PathVariable String email) throws Exception{
        return customerService.viewHistory(email);
    }
}
