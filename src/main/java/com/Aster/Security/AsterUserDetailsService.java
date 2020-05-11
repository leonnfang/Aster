package com.Aster.Security;

import com.Aster.Model.Customer;
import com.Aster.Model.Florist;
import com.Aster.Repository.CustomerRepository;
import com.Aster.Repository.FloristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FloristRepository floristRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(customerRepository.customerExistsUsername(username)){
            Customer customer = customerRepository.findCustomerByUsername(username);
            return new AsterUserDetails(customer);
        }
        else{
            Florist florist = floristRepository.findFloristByUsername(username);
            return new AsterUserDetails(florist);
        }


    }
}
