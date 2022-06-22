package com.amazon.ata.deliveringonourpromise.orderfulfillmentservice;

import com.amazon.ata.deliveringonourpromise.ServiceClient;
import com.amazon.ata.deliveringonourpromise.types.Promise;
import com.amazon.ata.orderfulfillmentservice.OrderFulfillmentService;
import com.amazon.ata.orderfulfillmentservice.OrderPromise;

/**
 * Client for accessing the OrderFulfillmentService to retrieve Promises.
 */

public class OrderFulfillmentServiceClient {
    private OrderFulfillmentService opService;

    /**
     * Create new client that calls OFS with the given service object.
     * 
     * @param opService The OrderFulfillment service that this client will call.
     */
    public OrderFulfillmentServiceClient(OrderFulfillmentService opService) {
        this.opService = opService;
    }

    /**
     * Fetches the OrderPromise for the given order item ID.
     *
     * @param customerOrderItemId - String representing the order item ID to fetch the order for.
     * @return order Promise for the given order item ID.
     */

    public Promise getDeliveryPromiseByOrderItemId(String customerOrderItemId) {
        OrderPromise orderPromise = opService.getOrderPromise(customerOrderItemId);
        
        if (orderPromise == null) {
            return null;
        }

        //FIXME Do I need to add .withDeliveryDate()?
        return Promise.builder()
                .withPromiseLatestArrivalDate(orderPromise.getPromiseLatestArrivalDate())
                .withCustomerOrderItemId(orderPromise.getCustomerOrderItemId())
                .withPromiseLatestShipDate(orderPromise.getPromiseLatestShipDate())
                .withPromiseEffectiveDate(orderPromise.getPromiseEffectiveDate())
                .withIsActive(orderPromise.isActive())
                .withPromiseProvidedBy(orderPromise.getPromiseProvidedBy())
                .withAsin(orderPromise.getAsin())
                .build();
    }
    
}
