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
    public FloristController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/makeorder")
    public int makeOrder(@RequestBody Order order) throws Exception {
        return orderService.addOrder(order);
    }
    @PostMapping("/addflorist")
    public int Add_User(@RequestBody User user) throws Exception {
        return floristService.addFlorist((Florist) user);
    }
}
