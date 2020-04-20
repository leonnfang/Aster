package com.Aster.Database;

import com.Aster.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT CASE WHEN count(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.email = ?1")
    boolean containsEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Customer findByEmail(String email);

}
