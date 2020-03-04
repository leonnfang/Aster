package com.Aster.Controller;
import com.Aster.Model.Florist;
import com.Aster.Model.History;
import com.Aster.Model.Order;
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
        this.orderService = orderService;
        this.floristService = floristService;
    }
    @PostMapping("/make_order")
    public int makeOrder(@RequestBody Order order) throws Exception {
        return orderService.addOrder(order);
    }
    @PostMapping("/cancel_order")
    public int cancelOrder(@RequestBody Order order) throws Exception{
        return orderService.cancelOrder(order);
    }
    @PostMapping("/add_florist")
    public int addFlorist(@RequestBody Florist florist) throws Exception {
        return floristService.addFlorist(florist);
    }
    @PostMapping("/get_florist")
    public Florist getFlorist(@RequestBody String email) throws Exception{
        return floristService.getFlorist(email);
    }
    @PostMapping("/delete_florist")
    public int deleteFlorist(@RequestBody String email) throws Exception{
        return floristService.deleteFlorist(email);
    }
    @PostMapping("/getUser_name")
    public String getUser_name(@RequestBody String email) throws Exception{
        return floristService.getUser_name(email);
    }
    @PostMapping("/getHistory")
    public History getHistory(@RequestBody String email) throws Exception{
        return floristService.getHistory(email);
    }
    @PostMapping("init_inventory")
    public int initInventory(@RequestBody String email) throws Exception{
        return floristService.initInventory(email);
    }
    @PostMapping("/update_inventory")
    public int updateInventory(@RequestBody String email, String product, int quantity) throws Exception{
        return floristService.updateInventory(email,product,quantity);
    }
}
