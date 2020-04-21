package com.Aster.Controller;
import com.Aster.Model.*;
import com.Aster.Service.FloristService;
import com.Aster.Service.JpaFloristService;
import com.Aster.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/florist")
public class FloristController{
    private OrderService orderService;
    private FloristService floristService;
    private JpaFloristService jpaFloristService;

    @Autowired
    public FloristController(OrderService orderService, FloristService floristService, JpaFloristService jpaFloristService) {
        this.orderService = orderService;
        this.floristService = floristService;
        this.jpaFloristService = jpaFloristService;
    }


    @PostMapping("/add")
    public boolean addFlorist(@RequestBody FloristJpa floristJpa) throws Exception {
        return jpaFloristService.addFlorist(floristJpa);
    }
    @ResponseBody
    @GetMapping("/get")
    public FloristJpa getFlorist(@RequestParam String email) throws Exception{
        return jpaFloristService.getFlorist(email);
    }
    @PostMapping("/{email}/delete")
    public boolean deleteFlorist(@PathVariable String email) throws Exception{
        return jpaFloristService.deleteFlorist(email);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public List<String> viewFlorists(){
        return jpaFloristService.viewFlorists();
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
