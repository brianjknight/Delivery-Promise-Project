package com.amazon.ata.deliveringonourpromise.comparator;

import com.amazon.ata.deliveringonourpromise.comparators.PromiseAsinComparator;
import com.amazon.ata.deliveringonourpromise.types.Promise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PromiseAsinComparatorTest {
    PromiseAsinComparator promiseAsinComparator;

    @BeforeEach
    private void setup() {
        promiseAsinComparator = new PromiseAsinComparator();
    }

    @Test
    public void compare_promiseWithEarlierAsinToLaterAsin_assertsTrueNegativeResult() {
        //GIVEN
        Promise promiseA = Promise.builder()
                .withAsin("AAA")
                .build();
        Promise promiseB = Promise.builder()
                .withAsin("BBB")
                .build();

        //WHEN
        int result = promiseAsinComparator.compare(promiseA, promiseB);

        //THEN
        assertTrue(result < 0, "Expected a negative number comparing ASIN AAA to ASIN BBB.");
    }

    @Test
    public void compare_promiseWithLaterAsinToEarlierAsin_assertsTruePositiveResult() {
        //GIVEN
        Promise promiseA = Promise.builder()
                .withAsin("AAA")
                .build();
        Promise promiseB = Promise.builder()
                .withAsin("BBB")
                .build();

        //WHEN
        int result = promiseAsinComparator.compare(promiseB, promiseA);

        //THEN
        assertTrue(result > 0, "Expected a negative number comparing ASIN BBB to ASIN AAA.");
    }

    @Test
    public void compare_promiseWithSameAsin_assertsTruePositiveResult() {
        //GIVEN
        Promise promiseA = Promise.builder()
                .withAsin("AAA")
                .build();
        Promise promiseA2 = Promise.builder()
                .withAsin("AAA")
                .build();

        //WHEN
        int result = promiseAsinComparator.compare(promiseA, promiseA2);

        //THEN
        assertTrue(result == 0, "Expected a zero comparing ASIN AAA to ASIN AAA.");
    }

    @Test
    public void compare_promiseWithAsinToNullAsin_assertsTrueNegativeResult() {
        //GIVEN
        Promise promiseA = Promise.builder()
                .withAsin("AAA")
                .build();
        Promise promiseNullAsin = Promise.builder()
                .build();

        //WHEN
        int result = promiseAsinComparator.compare(promiseA, promiseNullAsin);

        //THEN
        assertTrue(result < 0, "Expected a negative number comparing existing ASIN to null ASIN");
    }

    @Test
    public void compare_promiseWithNullAsinToExistingAsin_assertsTruePositiveResult() {
        //GIVEN
        Promise promiseA = Promise.builder()
                .withAsin("AAA")
                .build();
        Promise promiseNullAsin = Promise.builder()
                .build();

        //WHEN
        int result = promiseAsinComparator.compare(promiseNullAsin, promiseA);

        //THEN
        assertTrue(result > 0, "Expected a positive number comparing existing null ASIN to existing ASIN");
    }

    @Test
    public void compare_promiseSameReference_assertsTrueZeroResult() {
        //GIVEN
        Promise promiseA = Promise.builder()
                .withAsin("AAA")
                .build();
        Promise promiseCopyA = promiseA;

        //WHEN
        int result = promiseAsinComparator.compare(promiseCopyA, promiseA);

        //THEN
        assertTrue(result == 0, "Expected a zero comparing promises with same reference.");
    }

    @Test
    public void compare_promisesBothNullAsin_assertsTrueZeroResult() {
        //GIVEN
        Promise promiseNullAsinA = Promise.builder()
                .build();
        Promise promiseNullAsinB = Promise.builder()
                .build();

        //WHEN
        int result = promiseAsinComparator.compare(promiseNullAsinA, promiseNullAsinB);

        //THEN
        assertTrue(result == 0, "Expected a zero comapring null ASINs.");
    }

}
