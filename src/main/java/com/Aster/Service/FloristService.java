package com.Aster.Service;

import com.Aster.Model.*;
import com.Aster.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public boolean addFlorist(Florist florist) throws Exception{
        if(florist == null){
            throw new Exception("Invalid Florist");
        }
        if(floristRepository.floristExists(florist.getEmail())){
            throw new Exception("Florist Already Exists");
        }

        Inventory inventory = new Inventory();
        HistoryF historyF = new HistoryF();

        inventory.setFlorist(florist);
        florist.setInventory(inventory);
        historyF.setFlorist(florist);
        florist.setHistoryF(historyF);

        floristRepository.save(florist);
        return true;
    }
    public boolean deleteFlorist(String floristEmail) throws Exception {
        if(floristEmail == null){
            throw new Exception("Invalid Email");
        }
        if(!floristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }
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
        //check if purchase is already complete
        //
        return true;
    }
}
