package com.Aster.Repository;
import java.util.*;
public class EmailList {
    private List<Customer> customersList = new ArrayList<>();
    private List<Florist> floristList = new ArrayList<>();
    public List<Customer> getCustomersList() {
        return customersList;
    }
    public List<Florist> getFloristList() {
        return floristList;
    }

    public void setCustomersList(List<Customer> customersList) {
        this.customersList = customersList;
    }

    public void setFloristList(List<Florist> floristList) {
        this.floristList = floristList;
    }
    public void iterateFlorist(){
        for(Customer e : customersList){

        }
    }
    public void iterateCustomer(){
        for(Florist e : floristList){

        }
    }
}
