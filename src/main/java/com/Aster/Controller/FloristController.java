package com.Aster.Controller;
import com.Aster.Model.*;
import com.Aster.Service.FloristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/florist")
public class FloristController{

    private FloristService floristService;

    @Autowired
    public FloristController(FloristService floristService) {
        this.floristService = floristService;
    }


    @PostMapping("/add")
    public boolean addFlorist(@RequestBody Florist florist) throws Exception {
        return floristService.addFlorist(florist);
    }
    @ResponseBody
    @GetMapping("/get")
    public Florist getFlorist(@RequestParam String floristEmail) throws Exception{
        return floristService.getFlorist(floristEmail);
    }
    @PostMapping("/{florstEmail}/delete")
    public boolean deleteFlorist(@PathVariable String florstEmail) throws Exception{
        return floristService.deleteFlorist(florstEmail);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public List<Florist> viewFlorists(){
        return floristService.viewFlorists();
    }


    @PostMapping("/{floristEmail}/inventory/add")
    public boolean addInventory(@PathVariable String floristEmail,
                                @RequestBody Product product) throws Exception{
        return floristService.addInventory(floristEmail, product);
    }
    @DeleteMapping("/{floristEmail}/inventory/remove")
    public boolean removeInventory(@PathVariable String floristEmail,
                                   @RequestParam String productName) throws Exception{
        return floristService.removeInventory(floristEmail, productName);
    }
    @ResponseBody
    @GetMapping("/{floristEmail}/inventory/view")
    public List<Product> viewInventory(@PathVariable String floristEmail) throws Exception {
        return floristService.viewInventory(floristEmail);
    }
    @PostMapping("/{floristEmail}/inventory/empty")
    public boolean emptyInventory(@PathVariable String floristEmail) throws Exception{
        return floristService.emptyInventory(floristEmail);
    }


    /*
    @GetMapping("/{email}/history/view")
    public HistoryC getHistory(@PathVariable String email) throws Exception{
        return floristService.getHistory(email);
    }

     */


}
