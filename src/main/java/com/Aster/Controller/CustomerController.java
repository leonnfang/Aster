package com.Aster.Controller;
import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
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
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> getCustomer(@RequestParam String customerEmail) throws Exception{
        return new ResponseEntity<>(customerService.getCustomer(customerEmail), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/getbyusername")
    @PreAuthorize("#username == authentication.principal.getUsername()")
    public ResponseEntity<?> getCustomerByUsername(@RequestParam String username) throws Exception{
        return new ResponseEntity<>(customerService.getCustomerByUsername(username), HttpStatus.OK);
    }
    @DeleteMapping("/{customerEmail}/delete")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> deleteCustomer(@PathVariable String customerEmail) throws Exception{
        return new ResponseEntity<>(customerService.deleteCustomer(customerEmail), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public ResponseEntity<?> viewCustomers(){
        return new ResponseEntity<>(customerService.viewCustomers(), HttpStatus.OK);
    }


    @PostMapping("/{customerEmail}/cart/add")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> addCart(@PathVariable String customerEmail,
                           @RequestBody Purchase purchase) throws Exception{
        return new ResponseEntity<>(customerService.addCart(customerEmail, purchase), HttpStatus.OK);
    }
    @DeleteMapping("/{customerEmail}/cart/remove")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> removeCart(@PathVariable String customerEmail,
                              @RequestParam String orderID) throws Exception{
        return new ResponseEntity<>(customerService.removeCart(customerEmail, orderID), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/{customerEmail}/cart/view")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> viewCart(@PathVariable String customerEmail) throws Exception{
        return new ResponseEntity<>(customerService.viewCart(customerEmail), HttpStatus.OK);
    }
    @DeleteMapping("/{customerEmail}/cart/empty")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> emptyCart(@PathVariable String customerEmail) throws Exception{
        return new ResponseEntity<>(customerService.emptyCart(customerEmail), HttpStatus.OK);
    }


    @PutMapping("/{customerEmail}/checkout")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> checkout(@PathVariable String customerEmail) throws Exception {
        return new ResponseEntity<>(customerService.checkout(customerEmail), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("{customerEmail}/history/view")
    @PreAuthorize("#customerEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> viewHistory(@PathVariable String customerEmail) throws Exception{
        return new ResponseEntity<>(customerService.viewHistoryC(customerEmail), HttpStatus.OK);
    }


}
