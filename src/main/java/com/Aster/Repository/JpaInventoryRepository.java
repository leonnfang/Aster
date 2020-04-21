package com.Aster.Repository;

import com.Aster.Model.InventoryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface JpaInventoryRepository extends JpaRepository<InventoryJpa, Long> {
    @Modifying
    @Query("UPDATE InventoryJpa i SET i.totalNumber = i.totalNumber + ?2 WHERE i.Id = ?1")
    void totalNumberUpdate(Long floristId, int quantityToAdd);

    @Query("SELECT i.totalNumber FROM InventoryJpa i WHERE i.Id = ?1")
    int findTotalNumberById(Long floristId);

    @Modifying
    @Query("UPDATE InventoryJpa i SET i.isEmpty = true WHERE i.Id = ?1")
    void isEmptyUpdateTrue(Long floristId);
    @Modifying
    @Query("UPDATE InventoryJpa i SET i.isEmpty = false WHERE i.Id = ?1")
    void isEmptyUpdateFalse(Long floristId);

}
