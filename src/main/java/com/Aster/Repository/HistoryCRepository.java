package com.Aster.Repository;

import com.Aster.Model.HistoryC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HistoryCRepository extends JpaRepository<HistoryC, Long> {

    @Query("SELECT h.Id FROM HistoryC h WHERE h.customer.email = ?1")
    Long findHistoryCIdByEmail(String email);
}
