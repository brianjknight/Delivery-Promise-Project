package com.amazon.ata.deliveringonourpromise.dao;

import com.amazon.ata.deliveringonourpromise.data.OrderDatastore;
import com.amazon.ata.deliveringonourpromise.ordermanipulationauthority.OrderManipulationAuthorityClient;
import com.amazon.ata.deliveringonourpromise.types.Order;
import com.amazon.ata.ordermanipulationauthority.OrderManipulationAuthority;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderDaoTest {
    private OrderDatastore orderDatastore = OrderDatastore.getDatastore();
    private OrderManipulationAuthority service = new OrderManipulationAuthority(orderDatastore);
    private OrderManipulationAuthorityClient omaClient = new OrderManipulationAuthorityClient(service);
    private OrderDao dao = new OrderDao(omaClient);

    //Happy case
    @Test
    public void get_forKnownOrderId_returnsOrder() {
        //GIVEN
        String orderId = "900-3746402-0000002";

        //WHEN
        Order order = dao.get(orderId);

        //THEN
        assertTrue(order != null);
    }

    @Test
    public void get_forInvalidOrderId_returnsNull() {
        //GIVEN
        String orderId = "12345";

        //WHEN
        Order order = dao.get(orderId);

        //THEN
        assertTrue(order == null);
    }

    @Test
    public void get_forNullInput_returnsNull() {
        //GIVEN
        String orderId = null;

        //WHEN
        Order order = dao.get(orderId);

        //THEN
        assertTrue(order == null);
    }



}
