package com.Aster.Repository;

import com.Aster.Model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHistoryRepository extends JpaRepository<History, Long> {
}
