## `OrderDao` test plan
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

### get_forKnownOrderId_returnsOrder
Happy case, verifying that the OrderDao can return an order.

#### Given
* An order ID that we know exists

#### When
1. We call `get()` with that order ID

### Then
* The result is not null

### orderDao_createsNewOrderDaoObject
Alternate case, test orderDao constructor?
Pass OrderManipulationAuthorityClient client into the constructor
#### Given
*A OrderManipulationAuthorityClient object
#### When
1. We call OrderDao() constructor with object 
### Then
* Object is created with no errors.

### get_forInvalidOrderId_returnsNull
Alternate case, invalid orderId returns null.

#### Given
* An invalid/non-existent order ID

#### When
1. We call `get()` with that order ID

### Then
* The result is null.

### get_forNullInput_returnsNull
Alternate case, null input for orderId.

#### Given
* null orderId

#### When
1. We call `get()` with that null order ID

### Then
* The result is null.


### get_forEmptyString_returnsNull()
Alternate case, "" string input for orderId returns null
#### Given
*orderId strings = ""
#### When
1. We call `get()` with the empty string
### Then
*Result is null.

### get_forValidOrderIdNonExistingOrder_returnsNull()
Alternate case, a valid orderId of non-existing order
#### Given
*orderId 900-0000000-0000000 (valid orderId but a non-existing order) 
#### When
1. We call `get()` with the orderId
### Then
*Returns null order

### get_forInvalidOrderIdFormat_returnNull
Alternate case, provide an orderId with an invalid format
#### Given
*orderId "90037-46401-0000001" which is a correct number but misplaced hyphen
#### When
1. We call `get()` with orderId in a bad format
### Then
*Result is null.

### get_forNonNumericOrderId_returnNull
Alternate case, provide an orderId with letters
#### Given
*orderId "abc-3746401-0000001" first three characters 900 replaced with abc.
#### When
1. We call `get()` with the non-numeric orderId.
### Then
*Result is null.

