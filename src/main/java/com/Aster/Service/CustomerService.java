package com.Aster.Service;

import com.Aster.Model.*;
import com.Aster.Repository.*;
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
    @Autowired
    private InventoryRepository inventoryRepository;

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

        if(purchaseRepository.purchaseExists(customerEmail, purchase.getProductName(), customer.getCart())){
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
        return purchaseRepository.findPurchasesByCartId(cartId);
    }
    public boolean emptyCart(String customerEmail) throws Exception{
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        Long cartId = cartRepository.findCartIdByEmail(customerEmail);
        List<Purchase> cartToEmpty = purchaseRepository.findPurchasesByCartId(cartId);
        purchaseRepository.deleteInBatch(cartToEmpty);
        cartRepository.totalpriceToZero(cartId);
        return true;
    }

    public List<Purchase> viewHistoryC(String customerEmail) throws Exception{
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        Long historyCId = historyCRepository.findHistoryCIdByEmail(customerEmail);
        return purchaseRepository.findPurchasesByHistoryCId(historyCId);
    }
    public boolean checkout(String customerEmail) throws Exception{
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        //TODO make payment
        double totalPrice = cartRepository.findTotalpriceByCustomerEmail(customerEmail);

        Long cartId = cartRepository.findCartIdByEmail(customerEmail);
        List<Purchase> editCartList = purchaseRepository.findPurchasesByCartId(cartId);
        Customer customer = customerRepository.findCustomerByEmail(customerEmail);

        for(Purchase purchase : editCartList){
            if(purchase.getQuantity() > productRepository.findQuantityByEmailAndName(purchase.getFloristEmail(), purchase.getProductName())){
                throw new Exception("Not Enough Quantity In Florist's Inventory: " + purchase.getProductName());
            }
            Florist florist = floristRepository.findFloristByEmail(purchase.getFloristEmail());

            productRepository.quantityUpdate(purchase.getFloristEmail(), purchase.getProductName(), -1*purchase.getQuantity());
            purchase.setHistoryF(florist.getHistoryF());
            purchase.setHistoryC(customer.getHistoryC());
            purchase.setCart(null);

            inventoryRepository.totalNumberUpdate(florist.getId(), -1*purchase.getQuantity());
            if(inventoryRepository.findTotalNumberById(florist.getId()) == 0){
                inventoryRepository.isEmptyUpdateTrue(florist.getId());
            }
            else{
                inventoryRepository.isEmptyUpdateFalse(florist.getId());
            }
        }
        cartRepository.totalpriceToZero(cartId);

        return true;
    }
    public boolean cancelOrder(String customerEmail, String orderId) throws Exception{
        if(!customerRepository.customerExists(customerEmail)){
            throw new Exception("Customer Does Not Exist");
        }
        if(purchaseRepository.purchaseIsComplete(orderId)){
            throw new Exception("Delivery Is Already On Its Way");
        }
        Purchase purchase = purchaseRepository.findPurchaseByOrderId(orderId);
        int quantityToRestore = purchase.getQuantity();
        productRepository.quantityUpdate(purchase.getFloristEmail(), purchase.getProductName(), quantityToRestore);

        Florist florist = floristRepository.findFloristByEmail(purchase.getFloristEmail());
        inventoryRepository.totalNumberUpdate(florist.getId(), quantityToRestore);

        purchaseRepository.deleteById(orderId);

        //TODO return payment
        return true;
    }
}
