package com.Aster.Controller;
import com.Aster.Database.UserDB;
import com.Aster.Model.Order;
import com.Aster.Model.User;
import com.Aster.Service.MakeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class OrderController {
    private MakeOrder orderbuilder;

    //Error!!
    @Autowired
    public OrderController(MakeOrder order){
        this.orderbuilder = order;
    }

    @PostMapping("/PLACE_order")
    public int Place_order(@RequestBody Order order){
        orderbuilder.Add_order();
        return 0;
    }
}
