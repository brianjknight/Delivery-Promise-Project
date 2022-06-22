package com.amazon.ata.deliveringonourpromise;

import com.amazon.ata.deliveringonourpromise.types.Promise;

/**
 * Client interface to abstract calls
 */
public interface ServiceClient {
    /**
     * Get object method to be implemented.
     * @param customerOrderItemId Order item ID>
     * @return Abstracted object of type T.
     */
     Promise getDeliveryPromiseByOrderItemId(String customerOrderItemId);

}
