package com.Aster.Repository;

import com.Aster.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT CASE WHEN count(p) > 0 THEN true ELSE false END FROM Product p WHERE (p.florsitEmail = ?1 AND p.name = ?2)")
    boolean productExists(String floristEmail, String productName);

    @Query("SELECT p.quantity FROM Product p WHERE (p.florsitEmail = ?1 AND p.name = ?2)")
    int findQuantityByEmailAndName(String floristEmail, String productName);

    @Query("SELECT p.price FROM Product p WHERE (p.florsitEmail = ?1 AND p.name = ?2)")
    double findPriceByEmailAndName(String floristEmail, String productName);

    @Query("SELECT p FROM Product p WHERE (p.florsitEmail = ?1 AND p.name = ?2)")
    Product findProductByEmailAndName(String floristEmail, String productName);

    @Query("SELECT p FROM Product p WHERE p.florsitEmail = ?1")
    List<Product> findProductsByEmail(String floristEmail);

    @Modifying
    @Query("UPDATE Product p SET p.quantity = p.quantity + ?3 WHERE (p.florsitEmail = ?1 AND p.name = ?2) ")
    void quantityUpdate(String floristEmail, String productName, int quantityToAdd);
}
