package com.Aster.Repository;

import com.Aster.Model.HistoryF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HistoryFRepository extends JpaRepository<HistoryF, Long> {

    @Query("SELECT h.Id FROM HistoryF h WHERE h.florist.email = ?1")
    Long findHistoryFIdByEmail(String email);
}
