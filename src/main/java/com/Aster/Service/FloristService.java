package com.Aster.Service;

import com.Aster.Model.*;
import com.Aster.Repository.*;
import com.Aster.Security.AsterUserDetailsService;
import com.Aster.Security.AuthenticationResponse;
import com.Aster.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FloristService {
    @Autowired
    private FloristRepository floristRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private HistoryFRepository historyFRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private AsterUserDetailsService asterUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationResponse addFlorist(Florist florist) throws Exception{
        if(florist == null){
            throw new Exception("Invalid Florist");
        }
        if(floristRepository.floristExists(florist.getEmail())){
            throw new Exception("Florist Already Exists");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Florist newFlorist = new Florist(florist.getUsername(), bCryptPasswordEncoder.encode(florist.getPassword()),
                                         florist.getEmail(), florist.getAddress(),
                                         florist.getLastName(), florist.getFirstName());

        Inventory inventory = new Inventory();
        HistoryF historyF = new HistoryF();
        inventory.setFlorist(newFlorist);
        newFlorist.setInventory(inventory);
        historyF.setFlorist(newFlorist);
        newFlorist.setHistoryF(historyF);

        floristRepository.save(newFlorist);

        final UserDetails userDetails = asterUserDetailsService.loadUserByUsername(newFlorist.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }
    public boolean deleteFlorist(String floristEmail) throws Exception {
        if(floristEmail == null){
            throw new Exception("Invalid Email");
        }
        if(!floristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }
        historyFRepository.delete(historyFRepository.findHistoryFByEmail(floristEmail));
        inventoryRepository.delete(inventoryRepository.findInventoryByEmail(floristEmail));
        floristRepository.delete(floristRepository.findFloristByEmail(floristEmail));
        return true;
    }
    public Florist getFlorist(String floristEmail) throws Exception{
        if(floristEmail == null){
            throw new Exception("Invalid Email");
        }
        if(!floristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }
        return floristRepository.findFloristByEmail(floristEmail);
    }
    public Florist getFloristByUsername(String username) throws Exception{
        if(username == null){
            throw new Exception("Invalid Email");
        }
        if(!floristRepository.floristExistsByUsername(username)){
            throw new Exception("Florist Does Not Exist");
        }
        return floristRepository.findFloristByUsername(username);
    }
    public List<Florist> viewFlorists(){
        return floristRepository.findAllFlorists();
    }

    public boolean addInventory(String floristEmail, Product product)throws Exception{
        if(!floristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }

        Florist florist = floristRepository.findFloristByEmail(floristEmail);
        if(productRepository.productExists(floristEmail, product.getName())){

            int curQuantity = productRepository.findQuantityByEmailAndName(floristEmail, product.getName());
            if(curQuantity + product.getQuantity() < 0){
                throw new Exception("Total Quantity Cannot Be Less Than 0");
            }
            productRepository.quantityUpdate(floristEmail, product.getName(), product.getQuantity());
        }
        else{
            product.setInventory(florist.getInventory());
            productRepository.save(product);
        }

        inventoryRepository.totalNumberUpdate(florist.getId(), product.getQuantity());

        if(inventoryRepository.findTotalNumberById(florist.getId()) == 0){
            inventoryRepository.isEmptyUpdateTrue(florist.getId());
        }
        else{
            inventoryRepository.isEmptyUpdateFalse(florist.getId());
        }
        return true;
    }
    public boolean removeInventory(String floristEmail, String productName) throws Exception{
        if(!floristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }
        if(!productRepository.productExists(floristEmail, productName)){
            throw new Exception("Product Does Not Exist In Florist's Inventory");
        }
        productRepository.delete(productRepository.findProductByEmailAndName(floristEmail, productName));
        return true;
    }
    public List<Product> viewInventory(String floristEmail) throws Exception{
        if(!floristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }
        return productRepository.findProductsByEmail(floristEmail);
    }
    public boolean emptyInventory(String floristEmail) throws Exception{
        if(!floristRepository.floristExists(floristEmail)){
            throw new Exception("Forist Does Not Exist");
        }
        List<Product> InventoryToEmpty = productRepository.findProductsByEmail(floristEmail);
        productRepository.deleteInBatch(InventoryToEmpty);
        return true;
    }

    public List<Purchase> viewHistory(String floristEmail) throws Exception{
        if(!floristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }
        Long historyFId = historyFRepository.findHistoryFIdByEmail(floristEmail);
        return purchaseRepository.findPurchasesByHistoryFId(historyFId);
    }
    public boolean completeOrder(String orderId) throws Exception{
        if(!purchaseRepository.purchaseExistsById(orderId)){
            throw new Exception("Order Does Not Exist");
        }
        if(purchaseRepository.purchaseIsComplete(orderId)){
            throw new Exception("This Order Is Already Completed");
        }
        Purchase purchase = purchaseRepository.findPurchaseByOrderId(orderId);
        purchase.setComplete(true);
        return true;
    }
}
