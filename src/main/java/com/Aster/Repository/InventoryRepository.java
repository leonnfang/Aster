package com.Aster.Repository;

import com.Aster.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Modifying
    @Query("UPDATE Inventory i SET i.totalNumber = i.totalNumber + ?2 WHERE i.Id = ?1")
    void totalNumberUpdate(Long floristId, int quantityToAdd);

    @Query("SELECT i.totalNumber FROM Inventory i WHERE i.Id = ?1")
    int findTotalNumberById(Long floristId);

    @Modifying
    @Query("UPDATE Inventory i SET i.isEmpty = true WHERE i.Id = ?1")
    void isEmptyUpdateTrue(Long floristId);
    @Modifying
    @Query("UPDATE Inventory i SET i.isEmpty = false WHERE i.Id = ?1")
    void isEmptyUpdateFalse(Long floristId);

    @Query("SELECT i FROM Inventory i WHERE i.florist.email = ?1")
    Inventory findInventoryByEmail(String email);

}
