package com.Aster.Repository;

import com.Aster.Model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface JpaHistoryRepository extends JpaRepository<History, Long> {
}
