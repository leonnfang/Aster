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
    public Customer getCustomer(@RequestParam String customerEmail) throws Exception{
        return jpaCustomerService.getCustomer(customerEmail);
    }
    @DeleteMapping("/{customerEmail}/delete")
    public boolean deleteCustomer(@PathVariable String customerEmail) throws Exception{
        return jpaCustomerService.deleteCustomer(customerEmail);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public List<Customer> viewCustomers(){
        return jpaCustomerService.viewCustomers();
    }


    @PostMapping("/{customerEmail}/cart/add")
    public boolean addCart(@PathVariable String customerEmail,
                           @RequestBody Purchase purchase) throws Exception{
        return jpaCustomerService.addCart(customerEmail, purchase);
    }
    @DeleteMapping("/{customerEmail}/cart/remove")
    public boolean removeCart(@PathVariable String customerEmail,
                              @RequestParam String orderID) throws Exception{
        return jpaCustomerService.removeCart(customerEmail, orderID);
    }
    @ResponseBody
    @GetMapping("/{customerEmail}/cart/view")
    public List<Purchase> viewCart(@PathVariable String customerEmail) throws Exception{
        return jpaCustomerService.viewCart(customerEmail);
    }
    @DeleteMapping("/{customerEmail}/cart/empty")
    public boolean emptyCart(@PathVariable String customerEmail) throws Exception{
        return jpaCustomerService.emptyCart(customerEmail);
    }


    @PutMapping("/{customerEmail}/checkout")
    public boolean checkout(@PathVariable String customerEmail) throws Exception {
        return customerService.checkout(customerEmail);
    }
    @ResponseBody
    @GetMapping("{customerEmail}/history/view")
    public List<Purchase> viewHistory(@PathVariable String customerEmail) throws Exception{
        return customerService.viewHistory(customerEmail);
    }
}
