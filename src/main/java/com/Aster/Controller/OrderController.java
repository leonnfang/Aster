package com.Aster.Controller;
import com.Aster.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class OrderController {
    private OrderService orderbuilder;
    @Autowired
    public OrderController(OrderService order){
        this.orderbuilder = order;
    }

}
