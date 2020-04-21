package com.Aster.Repository;

import com.Aster.Model.InventoryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaInventoryRepository extends JpaRepository<InventoryJpa, Long> {
}
