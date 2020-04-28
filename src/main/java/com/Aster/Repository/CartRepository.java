package com.Aster.Repository;

import com.Aster.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Modifying
    @Query("UPDATE Cart c SET c.totalprice = (c.totalprice + ?2) WHERE c.Id = ?1")
    void totalpriceUpdate(Long cartId, double pricetoAdd);
    @Modifying
    @Query("UPDATE Cart c SET c.totalprice = 0 WHERE c.Id = ?1")
    void totalpriceToZero(Long cartId);


    @Query("SELECT c.totalprice FROM Cart c WHERE c.customer.email = ?1")
    double findTotalpriceByCustomerEmail(String customerEmail);

    @Query("SELECT c.Id FROM Cart c WHERE c.customer.email = ?1")
    Long findCartIdByEmail(String customerEmail);

    @Query("SELECT c FROM Cart c WHERE c.customer.email = ?1")
    Cart findCartByEmail(String customerEmail);
}
