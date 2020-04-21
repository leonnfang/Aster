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
    public FloristJpa getFlorist(@RequestParam String floristEmail) throws Exception{
        return jpaFloristService.getFlorist(floristEmail);
    }
    @PostMapping("/{florstEmail}/delete")
    public boolean deleteFlorist(@PathVariable String florstEmail) throws Exception{
        return jpaFloristService.deleteFlorist(florstEmail);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public List<FloristJpa> viewFlorists(){
        return jpaFloristService.viewFlorists();
    }


    @PostMapping("/{floristEmail}/inventory/add")
    public boolean addInventory(@PathVariable String floristEmail,
                                @RequestBody ProductJpa productJpa) throws Exception{
        return jpaFloristService.addInventory(floristEmail, productJpa);
    }
    @DeleteMapping("/{floristEmail}/inventory/remove")
    public boolean removeInventory(@PathVariable String floristEmail,
                                   @RequestParam String productName) throws Exception{
        return jpaFloristService.removeInventory(floristEmail, productName);
    }
    @ResponseBody
    @GetMapping("/{floristEmail}/inventory/view")
    public List<ProductJpa> viewInventory(@PathVariable String floristEmail) throws Exception {
        return jpaFloristService.viewInventory(floristEmail);
    }
    @PostMapping("/{floristEmail}/inventory/empty")
    public boolean emptyInventory(@PathVariable String floristEmail) throws Exception{
        return jpaFloristService.emptyInventory(floristEmail);
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
