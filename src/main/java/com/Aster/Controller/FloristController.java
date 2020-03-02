package com.Aster.Controller;
import com.Aster.Database.FloristDB;
import com.Aster.Model.Florist;
import com.Aster.Model.Order;
import com.Aster.Model.Product;
import com.Aster.Model.User;
import com.Aster.Service.FloristService;
import com.Aster.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        return orderService.cancelOrder(order);
    }
    @RequestMapping(value = "add_florist",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public int addUser(@RequestBody Florist florist) throws Exception {
        System.out.println("in controller layer");
        return floristService.addFlorist(florist);
    }
    @PostMapping("/delete_florist")
    public int deleteUser(@RequestParam String email) throws Exception {
        return floristService.deleteFlorist(email);
    }
    @PostMapping("update_inventory")
    public int updateInventory(@RequestBody Florist florist,
                               @RequestBody Product product,
                               @RequestBody int quantity) throws Exception {
        return floristService.updateInventory(florist,product,quantity);

    }
}
