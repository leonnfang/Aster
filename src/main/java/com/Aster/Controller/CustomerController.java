package com.Aster.Controller;
import com.Aster.Service.CustomerService;
import com.Aster.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private OrderService orderService;
    private CustomerService customerService;

    @Autowired
    public CustomerController(OrderService orderService) {
        this.orderService = orderService;
    }



}
