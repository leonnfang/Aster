package com.Aster.Database;
import com.Aster.Model.Customer;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class CustomerDB extends UserDB {
    Map<String, Customer> customerMap = new HashMap<>();

}
