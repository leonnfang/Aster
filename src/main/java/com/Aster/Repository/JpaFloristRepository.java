package com.Aster.Repository;

import com.Aster.Model.Customer;
import com.Aster.Model.FloristJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaFloristRepository extends JpaRepository<FloristJpa, Long> {
    @Query("SELECT CASE WHEN count(f) > 0 THEN true ELSE false END FROM FloristJpa f WHERE f.email = ?1")
    boolean floristExists(String email);

    @Query("SELECT f FROM FloristJpa f WHERE f.email = ?1")
    FloristJpa findByEmail(String email);

    @Query("SELECT f.email FROM FloristJpa f")
    List<String> findAllEmail();
}
