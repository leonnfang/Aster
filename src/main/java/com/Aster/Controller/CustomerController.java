package com.Aster.Controller;
import com.Aster.Model.Customer;
import com.Aster.Model.Purchase;
import com.Aster.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws Exception{
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/get")
    //@PreAuthorize("@check.sameCustomer(#customerEmail, authentication.principal.getEmail())")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public Customer getCustomer(@RequestParam String customerEmail) throws Exception{
        return customerService.getCustomer(customerEmail);
    }
    @DeleteMapping("/{customerEmail}/delete")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public boolean deleteCustomer(@PathVariable String customerEmail) throws Exception{
        return customerService.deleteCustomer(customerEmail);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public List<Customer> viewCustomers(){
        return customerService.viewCustomers();
    }


    @PostMapping("/{customerEmail}/cart/add")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public boolean addCart(@PathVariable String customerEmail,
                           @RequestBody Purchase purchase) throws Exception{
        return customerService.addCart(customerEmail, purchase);
    }
    @DeleteMapping("/{customerEmail}/cart/remove")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public boolean removeCart(@PathVariable String customerEmail,
                              @RequestParam String orderID) throws Exception{
        return customerService.removeCart(customerEmail, orderID);
    }
    @ResponseBody
    @GetMapping("/{customerEmail}/cart/view")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public List<Purchase> viewCart(@PathVariable String customerEmail) throws Exception{
        return customerService.viewCart(customerEmail);
    }
    @DeleteMapping("/{customerEmail}/cart/empty")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public boolean emptyCart(@PathVariable String customerEmail) throws Exception{
        return customerService.emptyCart(customerEmail);
    }


    @PutMapping("/{customerEmail}/checkout")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public boolean checkout(@PathVariable String customerEmail) throws Exception {
        return customerService.checkout(customerEmail);
    }
    @ResponseBody
    @GetMapping("{customerEmail}/history/view")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public List<Purchase> viewHistory(@PathVariable String customerEmail) throws Exception{
        return customerService.viewHistoryC(customerEmail);
    }


}
