package com.Aster.Controller;
import com.Aster.Database.FloristDB;
import com.Aster.Model.Florist;
import com.Aster.Model.Order;
import com.Aster.Model.User;
import com.Aster.Service.FloristService;
import com.Aster.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/florist")
public class FloristController{
    private OrderService orderService;
    private FloristService floristService;

    @Autowired
    public FloristController(OrderService orderService, FloristService floristService) {
        this.floristService = floristService;
        this.orderService = orderService;
    }

    @PostMapping("/make_order")
    public int makeOrder(@RequestBody Order order) throws Exception {
        return orderService.addOrder(order);
    }
    @PostMapping("/cancel_order")
    public int cancelOrder(@RequestBody Order order) throws Exception {
        orderService.cancelOrder(order);
        return 0;
    }
    @PostMapping("/add_florist")
    public int addUser(@RequestBody Florist florist) throws Exception {
        System.out.println("in controller layer");
        return floristService.addFlorist(florist);
    }
    @PostMapping("/delete_florist")
    public int deleteUser(@RequestBody String email){
        return 0;
    }
}
