package com.Aster.Database;

import com.Aster.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCartRepository extends JpaRepository<Cart, Long> {
}
