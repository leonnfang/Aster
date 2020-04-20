package com.Aster.Controller;
import com.Aster.Model.*;
import com.Aster.Service.FloristService;
import com.Aster.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public boolean addFlorist(@RequestBody Florist florist) throws Exception {
        return floristService.addFlorist(florist);
    }
    @ResponseBody
    @GetMapping("/get")
    public Florist getFlorist(@RequestParam String email) throws Exception{
        return floristService.getFlorist(email);
    }
    @PostMapping("/{email}/delete")
    public boolean deleteFlorist(@PathVariable String email) throws Exception{
        return floristService.deleteFlorist(email);
    }
    @GetMapping("/{email}/getUser_name")
    public String getUser_name(@PathVariable String email) throws Exception{
        return floristService.getUser_name(email);
    }


    @PostMapping("/{email}/inventory/add")
    public boolean add_product(@PathVariable String email,
                               @RequestParam int quantity,
                               @RequestBody Product product) throws Exception{
        return floristService.addProduct(email, product, quantity);
    }
    @DeleteMapping("/{email}/inventory/remove")
    public boolean remove_product(@PathVariable String email,
                                  @RequestBody Product product) throws Exception{
        return floristService.removeProduct(email, product);
    }
    @PostMapping("/{email}/inventory/update")
    public boolean updateInventory(@PathVariable String email,
                                   @RequestParam int quantity,
                                   @RequestBody Product product) throws Exception{
        return floristService.updateInventory(email,product,quantity);
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
    public boolean makeOrder(@RequestBody Purchase purchase) throws Exception {
        return orderService.addOrder(purchase);
    }
    @PostMapping("/cancel_order")
    public boolean cancelOrder(@RequestBody Purchase purchase) throws Exception{
        return orderService.cancelOrder(purchase);
    }
}
