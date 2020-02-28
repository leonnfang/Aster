package com.Aster.Service;

import com.Aster.Model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

public class MakeOrder {
    private Order order;

    public MakeOrder(Order order) {
        this.order = order;
    }
    public int Add_order(){
        return 0;
    }
}
