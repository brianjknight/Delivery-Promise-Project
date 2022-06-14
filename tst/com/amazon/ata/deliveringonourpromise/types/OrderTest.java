package com.amazon.ata.deliveringonourpromise.types;

import com.amazon.ata.deliveringonourpromise.App;
import com.amazon.ata.deliveringonourpromise.dao.OrderDao;
import com.amazon.ata.ordermanipulationauthority.OrderCondition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @BeforeEach
    //do I need to reset the App before each test?


    @Test
    public void getCustomerOrderItemList_externallyModifyCustomerOrderItemListIndexZeroToNull_assertNotNull() {
        //GIVEN
        Order order = App.getOrderDao().get("111-7497023-2960775");
        List<OrderItem> customerOrderItemList = order.getCustomerOrderItemList();
//        OrderItem initialOrderItem = order.getCustomerOrderItemList().get(0);
//        System.out.println("TEST initialOrderItem before calling .set(): " + initialOrderItem);

        //WHEN
        customerOrderItemList.set(0, null);
        OrderItem changedOrderItem = order.getCustomerOrderItemList().get(0);
//        System.out.println("TEST initialOrderItem after calling .set(): " + initialOrderItem);
//        System.out.println("TEST orderItem order.getCustomerOrderItemList().get(0): " + changedOrderItem);

        //THEN
        assertNotNull(changedOrderItem, "CustomerOrderItemList externally modified calling .set() on index 0.");
    }

    @Test
    public void getCustomerOrderItemList_externallyModifyClearAllItems_assertTrue() {
        //GIVEN
        Order order = App.getOrderDao().get("111-7497023-2960775");
        List<OrderItem> customerOrderItemList = order.getCustomerOrderItemList();
//        for (OrderItem orderItem : customerOrderItemList) {
//            System.out.println("OrderItem: " + orderItem);
//        }

        //WHEN
        customerOrderItemList.clear();
//        for (OrderItem orderItem : customerOrderItemList) {
//            System.out.println("OrderItem: " + orderItem);
//        }

        //THEN
        assertTrue(order.getCustomerOrderItemList().size() > 0, "CustomerOrderItemList externally modified removing elements by calling .clear().");
    }

    @Test
    public void getCustomerOrderItemList_externallyModifyAddOrderItem_assertEquals() {
        //GIVEN
        Order order = App.getOrderDao().get("111-7497023-2960775");
        List<OrderItem> customerOrderItemList = order.getCustomerOrderItemList();
        int expectedSize = customerOrderItemList.size();
        System.out.println("expectedSize = " + expectedSize);

        OrderItem otherOrderItem = App.getOrderDao().get("900-3746402-0000002").getCustomerOrderItemList().get(0);

        //WHEN
        customerOrderItemList.add(otherOrderItem);
        int actualSize = order.getCustomerOrderItemList().size();
        System.out.println("actualSize = " + actualSize);
        //THEN
        assertEquals(expectedSize, actualSize, "CustomerOrderItemList size was modified by adding an OrderItem.");
    }

    @Test
    public void getCustomerOrderItemList_directlyAddOrderItemToOrder_assertEquals() {
        //GIVEN
        Order order = App.getOrderDao().get("111-7497023-2960775");
        List<OrderItem> customerOrderItemList = order.getCustomerOrderItemList();
        int expectedSize = customerOrderItemList.size();
        System.out.println("expectedSize = " + expectedSize);
        OrderItem otherOrderItem = App.getOrderDao().get("900-3746402-0000002").getCustomerOrderItemList().get(0);

        //WHEN
        order.getCustomerOrderItemList().add(otherOrderItem);
        int actualSize = order.getCustomerOrderItemList().size();
        System.out.println("actualSize = " + actualSize);
        //THEN
        assertEquals(expectedSize, actualSize, "CustomerOrderItemList size was modified by adding an OrderItem.");
    }


}
