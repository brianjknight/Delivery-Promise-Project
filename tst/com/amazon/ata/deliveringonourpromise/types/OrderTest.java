package com.amazon.ata.deliveringonourpromise.types;

import com.amazon.ata.deliveringonourpromise.App;
import com.amazon.ata.deliveringonourpromise.dao.OrderDao;
import com.amazon.ata.ordermanipulationauthority.OrderCondition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void getCustomerOrderItemList_externallyModifyCustomerOrderItemListIndexZeroToNull_assertFalse() {
        //GIVEN
        Order order = App.getOrderDao().get("111-7497023-2960775");
        List<OrderItem> customerOrderItemList = order.getCustomerOrderItemList();
//        OrderItem initialOrderItem = order.getCustomerOrderItemList().get(0);

        //WHEN

        customerOrderItemList.set(0, null);
        OrderItem changedOrderItem = order.getCustomerOrderItemList().get(0);

//        System.out.println("initialOrderItem: " + initialOrderItem);
//        System.out.println("changedOrderItem: " + changedOrderItem);

        //THEN
        assertFalse(changedOrderItem == null, "CustomerOrderItemList within order variable was modified.");
    }

    @Test
    public void getCondition_externallyModifyOrderCondition_assertFalse() {
        //GIVEN
        //111-7497023-2960775
        //| ORDER DATE | ORDER ID | MARKETPLACE | TIMEZONE | CONDITION | SHIP OPTION | CUSTOMER ID |
        //| 2018-07-16T15:04:11 | 111-7497023-2960775 | 1 - US | UTC | 4 - Closed | second | 375944434 |
        Order order = App.getOrderDao().get("111-7497023-2960775");
        OrderCondition localOrderCondition = order.getCondition();
//        System.out.println("original order condition:  " + order.getCondition());

        //WHEN
        localOrderCondition = OrderCondition.CANCELLED;
//        System.out.println("localOrderCondition: " + localOrderCondition);
//        System.out.println("order.getCondition(): " + order.getCondition());

        //THEN
        assertFalse(order.getOrderId().equals(localOrderCondition), "OrderCondition was modified externally.");

    }


}
