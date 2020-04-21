package com.Aster.Repository;

import com.Aster.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPurchaseRepository extends JpaRepository<Purchase, String> {

}
