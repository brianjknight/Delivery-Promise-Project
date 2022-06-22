package com.amazon.ata.deliveringonourpromise.dao;

import com.amazon.ata.deliveringonourpromise.ServiceClient;
import com.amazon.ata.deliveringonourpromise.ordermanipulationauthority.OrderManipulationAuthorityClient;
import com.amazon.ata.deliveringonourpromise.types.Promise;
import com.amazon.ata.ordermanipulationauthority.OrderResult;
import com.amazon.ata.ordermanipulationauthority.OrderResultItem;
import com.amazon.ata.ordermanipulationauthority.OrderShipment;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation for Promises.
 */
public class PromiseDao implements ReadOnlyDao<String, List<Promise>> {
    private ServiceClient serviceClient;
    private ServiceClient secondServiceClient;
    private OrderManipulationAuthorityClient omaClient;

    /**
     * PromiseDao constructor, accepting service clients for DPS and OMA.
     * @param serviceClient A client for DAO to access DPS, OFS, or other service types.
     * @param omaClient OrderManipulationAuthorityClient for DAO to access OMA
     */
    public PromiseDao(ServiceClient serviceClient, ServiceClient secondServiceClient, OrderManipulationAuthorityClient omaClient) {
        //FIXME "It should also use the App class to get its clients, rather than instantiating them itself. "
        this.serviceClient = serviceClient;
        this.secondServiceClient = secondServiceClient;
        this.omaClient = omaClient;
    }

    /**
     * Returns a list of all Promises associated with the given order item ID.
     * @param customerOrderItemId the order item ID to fetch promise for
     * @return a List of promises for the given order item ID
     */
    @Override
    public List<Promise> get(String customerOrderItemId) {
        // Fetch the delivery date, so we can add to any promises that we find
        ZonedDateTime itemDeliveryDate = getDeliveryDateForOrderItem(customerOrderItemId);

        List<Promise> promises = new ArrayList<>();

        // fetch Promise from Delivery Promise Service. If exists, add to list of Promises to return.
        // Set delivery date
        Promise firstPromise = serviceClient.getDeliveryPromiseByOrderItemId(customerOrderItemId);
        if (firstPromise != null) {
            firstPromise.setDeliveryDate(itemDeliveryDate);
            promises.add(firstPromise);
        }
        // fetch Promise from Order Promise Service. If exists, add to list of Promises to return.
        // Set delivery date
        Promise secondPromise = secondServiceClient.getDeliveryPromiseByOrderItemId(customerOrderItemId);
        if (secondPromise != null) {
            secondPromise.setDeliveryDate(itemDeliveryDate);
            promises.add(secondPromise);
        }

        return promises;
    }

    /**
     * Fetches the delivery date of the shipment containing the order item specified by the given order item ID,
     * if there is one.
     *
     * If the order item ID doesn't correspond to a valid order item, or if the shipment hasn't been delivered
     * yet, return null.
     */
    private ZonedDateTime getDeliveryDateForOrderItem(String customerOrderItemId) {
        OrderResultItem orderResultItem = omaClient.getCustomerOrderItemByOrderItemId(customerOrderItemId);

        if (null == orderResultItem) {
            return null;
        }

        OrderResult orderResult = omaClient.getCustomerOrderByOrderId(orderResultItem.getOrderId());

        for (OrderShipment shipment : orderResult.getOrderShipmentList()) {
            for (OrderShipment.ShipmentItem shipmentItem : shipment.getCustomerShipmentItems()) {
                if (shipmentItem.getCustomerOrderItemId().equals(customerOrderItemId)) {
                    return shipment.getDeliveryDate();
                }
            }
        }

        // didn't find a delivery date!
        return null;
    }
}
