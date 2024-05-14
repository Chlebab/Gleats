select * from business;
select * from customer;
select * from menuitems;
select * from orderitems;
select * from orders;

SELECT 
    orders.orderId, 
    orders.orderTime,
    customer.customerEmailAddress, 
    business.businessName,
    menuItems.itemName
FROM 
    customer
JOIN 
    orders ON customer.customerId = orders.customerId
JOIN 
    orderItems ON orders.orderId = orderItems.orderId
JOIN 
    menuItems ON orderItems.itemId = menuItems.itemId
JOIN 
    business ON orders.businessId = business.businessId;

SELECT
	orders.*,
    orderItems.*
FROM
	orders
JOIN
	orderItems ON orders.orderId = orderItems.orderId;