package com.Aster.Repository;

import com.Aster.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    @Query("SELECT CASE WHEN count(p) > 0 THEN true ELSE false END FROM Purchase p WHERE (p.customerEmail = ?1 AND p.productName = ?2)")
    boolean purchaseExists(String customerEmail, String productName);
    @Query("SELECT CASE WHEN count(p) > 0 THEN true ELSE false END FROM Purchase p WHERE p.OrderId = ?1")
    boolean purchaseExistsById(String purchaseId);

    @Query("SELECT p.quantity FROM Purchase p WHERE (p.customerEmail = ?1 AND p.productName = ?2)")
    int findQuantityByEmailAndName(String customerEmail, String productName);
    @Query("SELECT p.quantity FROM Purchase p WHERE p.OrderId = ?1")
    int findQuantityByOrderId(String orderId);

    @Modifying
    @Query("UPDATE Purchase p SET p.quantity = p.quantity + ?3 WHERE (p.customerEmail = ?1 AND p.productName = ?2) ")
    void quantityUpdate(String customerEmail, String purchaseName, int quantityToAdd);

    @Query("SELECT p FROM Purchase p WHERE p.OrderId = ?1")
    Purchase findPurchaseByOrderId(String orderId);

    @Query("SELECT p FROM Purchase p WHERE p.customerEmail = ?1 AND p.cart.Id = ?2")
    List<Purchase> findPurchasesByEmailAndCartId(String customerEmail, Long cartId);
    @Query("SELECT p FROM Purchase p WHERE p.customerEmail = ?1 AND p.historyC.Id = ?2")
    List<Purchase> findPurchasesByEmailAndHistoryCId(String customerEmail, Long historyCId);
}
