package com.Aster.Repository;

import com.Aster.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT CASE WHEN count(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.email = ?1")
    boolean customerExists(String email);
    @Query("SELECT CASE WHEN count(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.username = ?1")
    boolean customerExistsByUsername(String username);

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Customer findCustomerByEmail(String email);
    @Query("SELECT c FROM Customer c WHERE c.username = ?1")
    Customer findCustomerByUsername(String username);


    @Query("SELECT c.email FROM Customer c")
    List<String> findAllEmail();
    @Query("SELECT c FROM Customer c")
    List<Customer> findAllCustomers();


}
