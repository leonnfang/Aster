package com.Aster.Service;

import com.Aster.Model.*;
import com.Aster.Repository.JpaFloristRepository;
import com.Aster.Repository.JpaInventoryRepository;
import com.Aster.Repository.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JpaFloristService {
    @Autowired
    private JpaFloristRepository jpaFloristRepository;
    @Autowired
    private JpaProductRepository jpaProductRepository;
    @Autowired
    private JpaInventoryRepository jpaInventoryRepository;

    public boolean addFlorist(FloristJpa floristJpa) throws Exception{
        if(floristJpa == null){
            throw new Exception("Invalid Florist");
        }
        if(jpaFloristRepository.floristExists(floristJpa.getEmail())){
            throw new Exception("Florist Already Exists");
        }

        InventoryJpa inventoryJpa = new InventoryJpa();
        History history = new History();

        inventoryJpa.setFloristJpa(floristJpa);
        floristJpa.setInventoryJpa(inventoryJpa);
        history.setFloristJpa(floristJpa);
        floristJpa.setHistory(history);

        jpaFloristRepository.save(floristJpa);
        return true;
    }
    public boolean deleteFlorist(String floristEmail) throws Exception {
        if(floristEmail == null){
            throw new Exception("Invalid Email");
        }
        if(!jpaFloristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }
        jpaFloristRepository.delete(jpaFloristRepository.findFloristByEmail(floristEmail));
        return true;
    }
    public FloristJpa getFlorist(String floristEmail) throws Exception{
        if(floristEmail == null){
            throw new Exception("Invalid Email");
        }
        if(!jpaFloristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }
        return jpaFloristRepository.findFloristByEmail(floristEmail);
    }
    public List<FloristJpa> viewFlorists(){
        return jpaFloristRepository.findAllFlorists();
    }

    public boolean addInventory(String floristEmail, ProductJpa productJpa)throws Exception{
        if(!jpaFloristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }

        FloristJpa floristJpa = jpaFloristRepository.findFloristByEmail(floristEmail);
        if(jpaProductRepository.productExists(floristEmail, productJpa.getName())){

            int curQuantity = jpaProductRepository.findQuantityByEmailAndName(floristEmail, productJpa.getName());
            if(curQuantity + productJpa.getQuantity() < 0){
                throw new Exception("Total Quantity Cannot Be Less Than 0");
            }
            jpaProductRepository.quantityUpdate(floristEmail, productJpa.getName(), productJpa.getQuantity());
        }
        else{
            productJpa.setInventoryJpa(floristJpa.getInventoryJpa());
            jpaProductRepository.save(productJpa);
        }

        jpaInventoryRepository.totalNumberUpdate(floristJpa.getId(), productJpa.getQuantity());

        if(jpaInventoryRepository.findTotalNumberById(floristJpa.getId()) == 0){
            jpaInventoryRepository.isEmptyUpdateTrue(floristJpa.getId());
        }
        else{
            jpaInventoryRepository.isEmptyUpdateFalse(floristJpa.getId());
        }
        return true;
    }
    public boolean removeInventory(String floristEmail, String productName) throws Exception{
        if(!jpaFloristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }
        if(!jpaProductRepository.productExists(floristEmail, productName)){
            throw new Exception("Product Does Not Exist In Florist's Inventory");
        }
        jpaProductRepository.delete(jpaProductRepository.findProductByEmailAndName(floristEmail, productName));
        return true;
    }
    public List<ProductJpa> viewInventory(String floristEmail) throws Exception{
        if(!jpaFloristRepository.floristExists(floristEmail)){
            throw new Exception("Florist Does Not Exist");
        }
        return jpaProductRepository.findProductsByEmail(floristEmail);
    }
    public boolean emptyInventory(String floristEmail) throws Exception{
        if(!jpaFloristRepository.floristExists(floristEmail)){
            throw new Exception("Forist Does Not Exist");
        }
        List<ProductJpa> InventoryToEmpty = jpaProductRepository.findProductsByEmail(floristEmail);
        jpaProductRepository.deleteInBatch(InventoryToEmpty);
        return true;
    }
}
