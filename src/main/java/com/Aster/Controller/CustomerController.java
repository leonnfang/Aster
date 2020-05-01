package com.Aster.Controller;
import com.Aster.Model.Customer;
import com.Aster.Model.Purchase;
import com.Aster.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public boolean addCustomer(@RequestBody Customer customer) throws Exception{
        return customerService.addCustomer(customer);
    }
    @ResponseBody
    @GetMapping("/get")
    public Customer getCustomer(@RequestParam String customerEmail) throws Exception{
        return customerService.getCustomer(customerEmail);
    }
    @DeleteMapping("/{customerEmail}/delete")
    public boolean deleteCustomer(@PathVariable String customerEmail) throws Exception{
        return customerService.deleteCustomer(customerEmail);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public List<Customer> viewCustomers(){
        return customerService.viewCustomers();
    }


    @PostMapping("/{customerEmail}/cart/add")
    public boolean addCart(@PathVariable String customerEmail,
                           @RequestBody Purchase purchase) throws Exception{
        return customerService.addCart(customerEmail, purchase);
    }
    @DeleteMapping("/{customerEmail}/cart/remove")
    public boolean removeCart(@PathVariable String customerEmail,
                              @RequestParam String orderID) throws Exception{
        return customerService.removeCart(customerEmail, orderID);
    }
    @ResponseBody
    @GetMapping("/{customerEmail}/cart/view")
    public List<Purchase> viewCart(@PathVariable String customerEmail) throws Exception{
        return customerService.viewCart(customerEmail);
    }
    @DeleteMapping("/{customerEmail}/cart/empty")
    public boolean emptyCart(@PathVariable String customerEmail) throws Exception{
        return customerService.emptyCart(customerEmail);
    }


    @PutMapping("/{customerEmail}/checkout")
    public boolean checkout(@PathVariable String customerEmail) throws Exception {
        return customerService.checkout(customerEmail);
    }
    @ResponseBody
    @GetMapping("{customerEmail}/history/view")
    public List<Purchase> viewHistory(@PathVariable String customerEmail) throws Exception{
        return customerService.viewHistoryC(customerEmail);
    }


}
