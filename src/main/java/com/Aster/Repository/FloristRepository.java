package com.Aster.Repository;

import com.Aster.Model.Florist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FloristRepository extends JpaRepository<Florist, Long> {
    @Query("SELECT CASE WHEN count(f) > 0 THEN true ELSE false END FROM Florist f WHERE f.email = ?1")
    boolean floristExists(String email);
    @Query("SELECT CASE WHEN count(f) > 0 THEN true ELSE false END FROM Florist f WHERE f.username = ?1")
    boolean floristExistsByUsername(String username);

    @Query("SELECT f FROM Florist f WHERE f.email = ?1")
    Florist findFloristByEmail(String email);
    @Query("SELECT f FROM Florist f WHERE f.username = ?1")
    Florist findFloristByUsername(String username);


    @Query("SELECT f.email FROM Florist f")
    List<String> findAllEmail();
    @Query("SELECT f FROM Florist f")
    List<Florist> findAllFlorists();
}
