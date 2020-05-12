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
    public ResponseEntity<?> getFlorist(@RequestParam String floristEmail) throws Exception{
        return new ResponseEntity<>(floristService.getFlorist(floristEmail), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/getbyusername")
    @PreAuthorize("#username == authentication.principal.getUsername()")
    public ResponseEntity<?> getFloristByUsername(@RequestParam String username) throws Exception{
        return new ResponseEntity<>(floristService.getFloristByUsername(username), HttpStatus.OK);
    }
    @DeleteMapping("/{florstEmail}/delete")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> deleteFlorist(@PathVariable String florstEmail) throws Exception{
        return new ResponseEntity<>(floristService.deleteFlorist(florstEmail), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/getAll")
    public ResponseEntity<?> viewFlorists(){
        return new ResponseEntity<>(floristService.viewFlorists(), HttpStatus.OK);
    }


    @PostMapping("/{floristEmail}/inventory/add")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> addInventory(@PathVariable String floristEmail,
                                @RequestBody Product product) throws Exception{
        return new ResponseEntity<>(floristService.addInventory(floristEmail, product), HttpStatus.OK);
    }
    @DeleteMapping("/{floristEmail}/inventory/remove")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> removeInventory(@PathVariable String floristEmail,
                                   @RequestParam String productName) throws Exception{
        return new ResponseEntity<>(floristService.removeInventory(floristEmail, productName), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/{floristEmail}/inventory/view")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> viewInventory(@PathVariable String floristEmail) throws Exception {
        return new ResponseEntity<>(floristService.viewInventory(floristEmail), HttpStatus.OK);
    }
    @PostMapping("/{floristEmail}/inventory/empty")
    @PreAuthorize("#floristEmail == authentication.principal.getEmail()")
    public ResponseEntity<?> emptyInventory(@PathVariable String floristEmail) throws Exception{
        return new ResponseEntity<>(floristService.emptyInventory(floristEmail), HttpStatus.OK);
    }


    /*
    @GetMapping("/{email}/history/view")
    public HistoryC getHistory(@PathVariable String email) throws Exception{
        return floristService.getHistory(email);
    }

     */


}
