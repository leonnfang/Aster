package com.Aster.Controller;
import com.Aster.Model.*;
import com.Aster.Service.FloristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/florist")
@CrossOrigin(origins = "http://localhost:3000")
public class FloristController{

    private FloristService floristService;

    @Autowired
    public FloristController(FloristService floristService) {
        this.floristService = floristService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addFlorist(@RequestBody Florist florist) throws Exception {
        return new ResponseEntity<>(floristService.addFlorist(florist), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/get")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
    public Florist getFlorist(@RequestParam String floristEmail) throws Exception{
        return floristService.getFlorist(floristEmail);
    }
    @DeleteMapping("/{florstEmail}/delete")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
    public boolean deleteFlorist(@PathVariable String florstEmail) throws Exception{
        return floristService.deleteFlorist(florstEmail);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public List<Florist> viewFlorists(){
        return floristService.viewFlorists();
    }


    @PostMapping("/{floristEmail}/inventory/add")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
    public boolean addInventory(@PathVariable String floristEmail,
                                @RequestBody Product product) throws Exception{
        return floristService.addInventory(floristEmail, product);
    }
    @DeleteMapping("/{floristEmail}/inventory/remove")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
    public boolean removeInventory(@PathVariable String floristEmail,
                                   @RequestParam String productName) throws Exception{
        return floristService.removeInventory(floristEmail, productName);
    }
    @ResponseBody
    @GetMapping("/{floristEmail}/inventory/view")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
    public List<Product> viewInventory(@PathVariable String floristEmail) throws Exception {
        return floristService.viewInventory(floristEmail);
    }
    @PostMapping("/{floristEmail}/inventory/empty")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
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
