package com.Aster.Service;

import com.Aster.Model.*;
import com.Aster.Repository.JpaFloristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaFloristService {
    @Autowired
    private JpaFloristRepository jpaFloristRepository;

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
    public boolean deleteFlorist(String email) throws Exception {
        if(email == null){
            throw new Exception("Invalid Email");
        }
        if(!jpaFloristRepository.floristExists(email)){
            throw new Exception("Florist Does Not Exist");
        }
        jpaFloristRepository.delete(jpaFloristRepository.findByEmail(email));
        return true;
    }
    public FloristJpa getFlorist(String email) throws Exception{
        if(email == null){
            throw new Exception("Invalid Email");
        }
        if(!jpaFloristRepository.floristExists(email)){
            throw new Exception("Florist Does Not Exist");
        }
        return jpaFloristRepository.findByEmail(email);
    }
    public List<String> viewFlorists(){
        return jpaFloristRepository.findAllEmail();
    }


}
