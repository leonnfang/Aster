package com.Aster.Security;

import com.Aster.Repository.CustomerRepository;
import com.Aster.Repository.FloristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AsterUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FloristRepository floristRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(customerRepository.customerExistsByUsername(username)){
            return new CustomerDetails(customerRepository.findCustomerByUsername(username));
        }
        else{
            return new FloristDetails(floristRepository.findFloristByUsername(username));
        }
    }
    /*
    @Component("check")
    public class check{
        public boolean sameCustomer(String customerEmail, String principal){
            System.out.println(customerEmail);
            System.out.println(principal);
            return true;
        }
    }

     */
}
