package com.Aster.Service;

import com.Aster.Repository.*;
import com.Aster.Model.Cart;
import com.Aster.Model.Customer;
import com.Aster.Model.HistoryC;
import com.Aster.Model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FloristRepository floristRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private HistoryCRepository historyCRepository;

    public boolean addCustomer(Customer customer) throws Exception{
        if(customer == null){
            throw new Exception("Invalid Customer");
        }
        if(customerRepository.customerExists(customer.getEmail())){
            throw new Exception("Customer Already Exists");
        }

        Cart cart = new Cart();
        HistoryC historyC = new HistoryC();

        cart.setCustomer(customer);
        customer.setCart(cart);
        historyC.setCustomer(customer);
        customer.setHistoryC(historyC);

        customerRepository.save(customer);
        return true;
    }
    public boolean deleteCustomer(String customerEmail) throws Exception{
        if(customerEmail == null){
            throw new Exception("Invalid Email");
        }
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        customerRepository.delete(customerRepository.findCustomerByEmail(customerEmail));
        return true;
    }
    public Customer getCustomer(String customerEmail) throws Exception{
        if(customerEmail == null){
            throw new Exception("Invalid Email");
        }
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        return customerRepository.findCustomerByEmail(customerEmail);
    }
    public List<Customer> viewCustomers(){
        return customerRepository.findAllCustomers();
    }

    public boolean addCart(String customerEmail, Purchase purchase) throws Exception{
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        if(!floristRepository.floristExists(purchase.getFloristEmail())){
            throw new Exception("Florist Does Not Exist");
        }
        if(!productRepository.productExists(purchase.getFloristEmail(), purchase.getProductName())){
            throw new Exception("Product Does Not Exist In Florist's Inventory");
        }
        int inventoryQuantity = productRepository.findQuantityByEmailAndName(purchase.getFloristEmail(), purchase.getProductName());
        if(purchase.getQuantity() > inventoryQuantity){
            throw new Exception("Not Enough Quantity In Florist's Inventory");
        }

        Customer customer = customerRepository.findCustomerByEmail(customerEmail);
        double priceToAdd = purchase.getQuantity() * productRepository.findPriceByEmailAndName(purchase.getFloristEmail(), purchase.getProductName());

        if(purchaseRepository.purchaseExists(customerEmail, purchase.getProductName())){
            if(inventoryQuantity < purchase.getQuantity() + purchaseRepository.findQuantityByEmailAndName(customerEmail, purchase.getProductName())){
                throw new Exception("Not Enough Quantity In Florist's Inventory, Cannot Add That Much Quantity");
            }
            purchaseRepository.quantityUpdate(customerEmail, purchase.getProductName(), purchase.getQuantity());
        }
        else{
            purchase.setCart(customer.getCart());
            purchaseRepository.save(purchase);
        }
        Long cartId = cartRepository.findCartIdByEmail(customerEmail);
        cartRepository.totalpriceUpdate(cartId, priceToAdd);

        return true;
    }
    public boolean removeCart(String customerEmail, String orderId) throws Exception{
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        if(!purchaseRepository.purchaseExistsById(orderId)){
            throw new Exception("Order Does Not Exist");
        }

        Purchase purchase = purchaseRepository.findPurchaseByOrderId(orderId);
        double price = productRepository.findPriceByEmailAndName(purchase.getFloristEmail(), purchase.getProductName());
        int quantity = purchase.getQuantity();
        double priceToReduce = price * quantity * -1;

        Long cartId = cartRepository.findCartIdByEmail(customerEmail);
        cartRepository.totalpriceUpdate(cartId, priceToReduce);
        purchaseRepository.deleteById(orderId);

        return true;
    }
    public List<Purchase> viewCart(String customerEmail) throws Exception{
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        Long cartId = cartRepository.findCartIdByEmail(customerEmail);
        return purchaseRepository.findPurchasesByEmailAndCartId(customerEmail, cartId);
    }
    public boolean emptyCart(String customerEmail) throws Exception{
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        Long cartId = cartRepository.findCartIdByEmail(customerEmail);
        List<Purchase> cartToEmpty = purchaseRepository.findPurchasesByEmailAndCartId(customerEmail, cartId);
        purchaseRepository.deleteInBatch(cartToEmpty);
        cartRepository.totalpriceToZero(cartId);
        return true;
    }

    public boolean checkout(String customerEmail) throws Exception{
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        //TODO make payment

        Long cartId = cartRepository.findCartIdByEmail(customerEmail);
        List<Purchase> editCartList = purchaseRepository.findPurchasesByEmailAndCartId(customerEmail, cartId);
        //update(reduce) Florist's Inventory
        for(Purchase purchase : editCartList){
            productRepository.quantityUpdate(purchase.getFloristEmail(), purchase.getProductName(), purchase.getQuantity());

        }
        //update Florist's History
        //update Customer's History
        //empty Customer's Cart

        return true;
    }
    public List<Purchase> viewHistoryC(String customerEmail) throws Exception{
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        Long historyCId = historyCRepository.findHistoryCIdByEmail(customerEmail);
        return purchaseRepository.findPurchasesByEmailAndHistoryCId(customerEmail, historyCId);
    }
    public boolean cancelPurchase(String customerEmail, String orderId) throws Exception{
        return true;
    }
}
