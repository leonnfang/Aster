package com.Aster.Database;

import com.Aster.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPurchaseRepository extends JpaRepository<Purchase, String> {

}
