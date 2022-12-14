## `Order` test plan
This is a sample test plan template. Amazon teams may have different test plan
expectations, different file formats, or even ignore test plans altogether.

Writing a test plan helps manage expectations and provides a goal before you
begin writing the actual test code.

Remember from the unit test lesson that ATA expects test names to follow the
pattern "methodName_testCase_expectedBehavior".

Every test plan has:
1. A name
    * A description
2. Given  
   The pre-conditions required for this test to work.
   ATA expects this to be an unordered list. The items should be
   related to the "testCase" portion of the test name.
3. When  
   The actions to take to achieve the desired result.
   ATA expects this to be an ordered list, because the actions to take are
   generally required to be taken in a particular order.
   The items should include the "methodName" from the test name.
4. Then  
   Testable results.
   ATA expects this to be an unordered list. The items should be
   related to the "expectedBehavior" portion of the test name.

We've filled out a happy path test for the `OrderDao#get` method.
Copy and modify it to complete your test plan.

### getCustomerOrderItemList_externallyModifyCustomerOrderItemListIndexZeroToNull_assertFalse()
Alternate case, get the customerOrderItemList from an order and externally modify the first OrderItem at index 0.
#### Given
* Create an Order object from orderId 111-7497023-2960775, then use getCustomerOrderItemList to create a local variable of type List<OrderItem> .
#### When
1. We set index zero of the local List variable to null.
### Then
* The result asserts false if the customerOrderItemList in Order object was modified. 

### getCustomerOrderItemList_externallyModifyClearAllItems_assertFalse()
Alternate case, get a non-empty customerOrderItemList from an order and externally modify by removing all the OrderItems.
#### Given
* Create an Order object from orderId 111-7497023-2960775, then use getCustomerOrderItemList to create a local variable of type List<OrderItem> .
#### When
1. We use .clear() to remove all elements from the list.
### Then
* The result asserts false if the customerOrderItemList in Order object was cleared; the list length will be zero.  

### getCustomerOrderItemList_externallyModifyAddOrderItem_assertFalse()
Alternate case, get a non-empty customerOrderItemList from an order
#### Given
* Create an Order object from orderId 111-7497023-2960775, then use getCustomerOrderItemList to create a local variable of type List<OrderItem> .
#### When
1. We use .add() to add an OrderItem to the list.
### Then
* The result asserts false if the customerOrderItemList in Order object was modified. 



### getCondition_externallyModifyOrderCondition_assertFalse()
Alternate case, Create an Order object from orderId 111-7497023-2960775 and modify its OrderCondition externally.
#### Given
* Create an Order object from orderId 111-7497023-2960775 
* Use getCondition() to save the OrderCondition to a local variable OrderCondition localCondition.
#### When
1. Set local variable to a different OrderCondition
### Then
* Result asserts false if the condition was changed from the Order object.
