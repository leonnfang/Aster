package com.Aster.Repository;

import com.Aster.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface JpaCartRepository extends JpaRepository<Cart, Long> {
    @Modifying
    @Query("UPDATE Cart c SET c.totalprice = c.totalprice + ?2 WHERE c.Id = ?1")
    void totalpriceUpdate(Long customerId, double pricetoAdd);

    @Query("SELECT c.totalprice FROM Cart c WHERE c.Id = ?1")
    double findTotalpriceByCustomerId(Long customerId);
}
