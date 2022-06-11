package com.amazon.ata.deliveringonourpromise.dao;

import com.amazon.ata.deliveringonourpromise.App;
import com.amazon.ata.deliveringonourpromise.data.OrderDatastore;
import com.amazon.ata.deliveringonourpromise.ordermanipulationauthority.OrderManipulationAuthorityClient;
import com.amazon.ata.deliveringonourpromise.types.Order;
import com.amazon.ata.ordermanipulationauthority.OrderManipulationAuthority;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderDaoTest {
    //I didn't need to create theclient here. App class was built for this purpose.
//    private OrderDatastore orderDatastore = OrderDatastore.getDatastore();
//    private OrderManipulationAuthority service = new OrderManipulationAuthority(orderDatastore);
//    private OrderManipulationAuthorityClient omaClient = new OrderManipulationAuthorityClient(service);
//    private OrderDao dao = new OrderDao(omaClient);

    //Happy case
    @Test
    public void get_forKnownOrderId_returnsOrder() {
        //GIVEN
        String orderId = "900-3746402-0000002";

        //WHEN
        Order order = App.getOrderDao().get(orderId);

        //THEN
        assertTrue(order != null);
    }

    @Test
    public void orderDao_createsNewOrderDaoObject() {
        OrderManipulationAuthorityClient omaClient = App.getOrderManipulationAuthorityClient();
        OrderDao orderDao = new OrderDao(omaClient);

    }

    @Test
    public void get_forInvalidOrderId_returnsNull() {
        //GIVEN
        String orderId = "12345";

        //WHEN
        Order order = App.getOrderDao().get(orderId);

        //THEN
        assertTrue(order == null);
    }

    @Test
    public void get_forNullInput_returnsNull() {
        //GIVEN
        String orderId = null;

        //WHEN
        Order order = App.getOrderDao().get(orderId);

        //THEN
        assertTrue(order == null);
    }

    @Test
    public void get_forEmptyString_returnsNull() {
        //GIVEN
        String orderId = "";

        //WHEN
        Order order = App.getOrderDao().get(orderId);

        //THEN
        assertTrue(order == null);
    }

    @Test
    public void get_forValidOrderIdNonExistingOrder_returnsNull() {
        //GIVEN
        String orderId = "900-0000000-0000000";

        //WHEN
        Order order = App.getOrderDao().get(orderId);

        //THEN
        assertTrue(order == null);
    }

    @Test
    public void get_forInvalidOrderIdFormat_returnNull() {
        //GIVEN
        String orderId = "90037-46401-0000001";

        //WHEN
        Order order = App.getOrderDao().get(orderId);

        //THEN
        assertTrue(order == null);
    }

    @Test
    public void get_forNonNumericOrderId_returnNull() {
        //GIVEN
        String orderId = "abc-3746401-0000001";

        //WHEN
        Order order = App.getOrderDao().get(orderId);

        //THEN
        assertTrue(order == null);
    }

}
