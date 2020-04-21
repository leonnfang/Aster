package com.Aster.Service;

import com.Aster.Repository.*;
import com.Aster.Model.Cart;
import com.Aster.Model.Customer;
import com.Aster.Model.History;
import com.Aster.Model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaCustomerService {
    @Autowired
    private JpaCustomerRepository jpaCustomerRepository;
    @Autowired
    private JpaFloristRepository jpaFloristRepository;
    @Autowired
    private JpaProductRepository jpaProductRepository;
    @Autowired
    private JpaPurchaseRepository jpaPurchaseRepository;
    @Autowired
    private JpaCartRepository jpaCartRepository;

    public boolean addCustomer(Customer customer) throws Exception{
        if(customer == null){
            throw new Exception("Invalid Customer");
        }
        if(jpaCustomerRepository.customerExists(customer.getEmail())){
            throw new Exception("Customer Already Exists");
        }

        Cart cart = new Cart();
        History history = new History();

        cart.setCustomer(customer);
        customer.setCart(cart);
        history.setCustomer(customer);
        customer.setHistory(history);

        jpaCustomerRepository.save(customer);
        return true;
    }
    public boolean deleteCustomer(String customerEmail) throws Exception{
        if(customerEmail == null){
            throw new Exception("Invalid Email");
        }
        if(!jpaCustomerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        jpaCustomerRepository.delete(jpaCustomerRepository.findCustomerByEmail(customerEmail));
        return true;
    }
    public Customer getCustomer(String customerEmail) throws Exception{
        if(customerEmail == null){
            throw new Exception("Invalid Email");
        }
        if(!jpaCustomerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        return jpaCustomerRepository.findCustomerByEmail(customerEmail);
    }
    public List<Customer> viewCustomers(){
        return jpaCustomerRepository.findAllCustomers();
    }

    public boolean addCart(String customerEmail, Purchase purchase) throws Exception{
        if(!jpaCustomerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        if(!jpaFloristRepository.floristExists(purchase.getFloristEmail())){
            throw new Exception("Florist Does Not Exist");
        }
        if(!jpaProductRepository.productExists(purchase.getFloristEmail(), purchase.getProductName())){
            throw new Exception("Product Does Not Exist In Florist's Inventory");
        }
        int inventoryQuantity = jpaProductRepository.findQuantityByEmailAndName(purchase.getFloristEmail(), purchase.getProductName());
        if(purchase.getQuantity() > inventoryQuantity){
            throw new Exception("Not Enough Quantity In Florist's Inventory");
        }

        Customer customer = jpaCustomerRepository.findCustomerByEmail(customerEmail);
        double priceToAdd = purchase.getQuantity() * jpaProductRepository.findPriceByEmailAndName(purchase.getFloristEmail(), purchase.getProductName());

        if(jpaPurchaseRepository.purchaseExists(customerEmail, purchase.getProductName())){
            if(inventoryQuantity < purchase.getQuantity() + jpaPurchaseRepository.findQuantityByEmailAndName(customerEmail, purchase.getProductName())){
                throw new Exception("Not Enough Quantity In Florist's Inventory, Cannot Add That Much Quantity");
            }
            jpaPurchaseRepository.quantityUpdate(customerEmail, purchase.getProductName(), purchase.getQuantity());
        }
        else{
            purchase.setCart(customer.getCart());
            jpaPurchaseRepository.save(purchase);
        }
        jpaCartRepository.totalpriceUpdate(customer.getId(), priceToAdd);

        return true;
    }
    public boolean removeCart(String customerEmail, String orderId) throws Exception{
        if(!jpaCustomerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        if(!jpaPurchaseRepository.purchaseExistsById(orderId)){
            throw new Exception("Order Does Not Exist");
        }

        Customer customer = jpaCustomerRepository.findCustomerByEmail(customerEmail);
        Purchase purchase = jpaPurchaseRepository.findPurchaseByOrderId(orderId);

        double price = jpaProductRepository.findPriceByEmailAndName(purchase.getFloristEmail(), purchase.getProductName());
        int quantity = purchase.getQuantity();
        double priceToReduce = price * quantity;

        jpaCartRepository.totalpriceUpdate(customer.getId(), priceToReduce);
        jpaPurchaseRepository.deleteById(orderId);

        return true;
    }
    public List<Purchase> viewCart(String customerEmail) throws Exception{
        if(!jpaCustomerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        return jpaPurchaseRepository.findPurchasesByEmail(customerEmail);
    }
    public boolean emptyCart(String customerEmail) throws Exception{
        if(!jpaCustomerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        List<Purchase> cartToEmpty = jpaPurchaseRepository.findPurchasesByEmail(customerEmail);
        jpaPurchaseRepository.deleteInBatch(cartToEmpty);
        return true;
    }


}
