package com.amazon.ata.deliveringonourpromise.comparators;

import com.amazon.ata.deliveringonourpromise.types.Promise;

import java.io.Serializable;
import java.util.Comparator;

public class PromiseAsinComparator implements Comparator<Promise>, Serializable {
    @Override
    public int compare(Promise promise1, Promise promise2) {
        if (promise1.getAsin() != null && promise2.getAsin() == null) {
            return -1;
        }

        if (promise1.getAsin() == null && promise2.getAsin() != null) {
            return 1;
        }
        if ((promise1 == promise2) || (promise1.getAsin() == null && promise2.getAsin() == null)) {
            return 0;
        }


        String asin1 = promise1.getAsin();
        String asin2 = promise2.getAsin();

        int result = asin1.compareTo(asin2);

        return result;
    }
}
