package com.amazon.ata.deliveringonourpromise.types;

import com.amazon.ata.deliveringonourpromise.App;
import com.amazon.ata.deliveringonourpromise.dao.OrderDao;
import com.amazon.ata.ordermanipulationauthority.OrderCondition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

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

    @Test
    void masteryTaskThree_orderClass_withCustomerOrderItemList_internalStateIsProtectedByDefensiveCopying() {
        // GIVEN
        OrderItem customerOrderItem = OrderItem.builder()
                .withCustomerOrderItemId("1")
                .build();

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(customerOrderItem);

        // Don't finish building the Order just yet...pass in the list,
        // then modify it before final build().
        //FIXME think I need to fix the Order.builder() to create a new reference for its List<OrderItem> list
        Order.Builder orderBuilder = Order.builder()
                .withCustomerOrderItemList(orderItemList);

        String maliciousCustomerOrderItemId = "2";
        OrderItem maliciousCustomerOrderItem = OrderItem.builder()
                .withCustomerOrderItemId(maliciousCustomerOrderItemId)
                .build();

        // WHEN - attempt to update the list that was already passed into the Order should
        // not modify the Order's list, even if modified before build()
        orderItemList.add(maliciousCustomerOrderItem);

        // THEN
        Order order = orderBuilder.build();
        //FIXME
        List<OrderItem> customerOrderItemList = order.getCustomerOrderItemList();
        assertEquals(
                customerOrderItemList.size(),
                1,
                "Expected only original OrderItem to exist in Order, but found: " + customerOrderItemList
        );
        String orderItemId = customerOrderItemList.get(0).getCustomerOrderItemId();
        assertNotEquals(orderItemId, maliciousCustomerOrderItemId,
                "Expected Order class to not allow item to be maliciously inserted but it was!");
    }
}
