package com.Aster.Controller;
import com.Aster.Model.*;
import com.Aster.Service.FloristService;
import com.Aster.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public int addFlorist(@RequestBody Florist florist) throws Exception {
        return floristService.addFlorist(florist);
    }
    @ResponseBody
    @GetMapping("/get")
    public Florist getFlorist(@RequestParam String email) throws Exception{
        return floristService.getFlorist(email);
    }
    @PostMapping("/{email}/delete")
    public int deleteFlorist(@PathVariable String email) throws Exception{
        return floristService.deleteFlorist(email);
    }
    @GetMapping("/{email}/getUser_name")
    public String getUser_name(@PathVariable String email) throws Exception{
        return floristService.getUser_name(email);
    }


    @PostMapping("/{email}/inventory/add")
    public int add_product(@PathVariable String email,
                           @RequestParam String productName,
                           @RequestParam String floristName,
                           @RequestParam String description){
        return floristService.addProduct(email,productName,floristName,description);
    }
    @PostMapping("/{email}/inventory/update")
    public int updateInventory(@PathVariable String email,
                               @RequestParam String productName,
                               @RequestParam int quantity) throws Exception{
        return floristService.updateInventory(email,productName,quantity);
    }
    @ResponseBody
    @GetMapping("/{email}/inventory/view")
    public Inventory viewInventory(@PathVariable String email) throws Exception {
        return floristService.viewInventory(email);
    }


    @GetMapping("/{email}/history/view")
    public History getHistory(@PathVariable String email) throws Exception{
        return floristService.getHistory(email);
    }


    @PostMapping("/make_order")
    public int makeOrder(@RequestBody Order order) throws Exception {
        return orderService.addOrder(order);
    }
    @PostMapping("/cancel_order")
    public int cancelOrder(@RequestBody Order order) throws Exception{
        return orderService.cancelOrder(order);
    }
}
