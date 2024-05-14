use takeaway;

-- Test Data for the 'business' table
INSERT INTO business (businessName, cuisine) VALUES ('The Burger Joint', 'American');
INSERT INTO business (businessName, cuisine) VALUES ('Curry House', 'Indian');
INSERT INTO business (businessName, cuisine) VALUES ('Noodle Bar', 'Chinese');
INSERT INTO business (businessName, cuisine) VALUES ('The Chippy', 'British');
INSERT INTO business (businessName, cuisine) VALUES ('Kebabs and More', 'Turkish');

-- Test Data for the 'menuItems' table
INSERT INTO menuItems (itemName, businessId) VALUES ('Cheese Burger', 1);
INSERT INTO menuItems (itemName, businessId) VALUES ('Chicken Burger', 1);
INSERT INTO menuItems (itemName, businessId) VALUES ('Veggie Burger', 1);
INSERT INTO menuItems (itemName, businessId) VALUES ('Chicken Tikka Masala', 2);
INSERT INTO menuItems (itemName, businessId) VALUES ('Lamb Biryani', 2);
INSERT INTO menuItems (itemName, businessId) VALUES ('Prawn Curry', 2);
INSERT INTO menuItems (itemName, businessId) VALUES ('Vegetable Biryani', 2);
INSERT INTO menuItems (itemName, businessId) VALUES ('Beef Chow Mein', 3);
INSERT INTO menuItems (itemName, businessId) VALUES ('Sweet and Sour Chicken', 3);
INSERT INTO menuItems (itemName, businessId) VALUES ('Pork Chow Mein', 3);
INSERT INTO menuItems (itemName, businessId) VALUES ('Crispy Duck', 3);
INSERT INTO menuItems (itemName, businessId) VALUES ('Cod and Chips', 4);
INSERT INTO menuItems (itemName, businessId) VALUES ('Haddock and Chips', 4);
INSERT INTO menuItems (itemName, businessId) VALUES ('Sausage and Chips', 4);
INSERT INTO menuItems (itemName, businessId) VALUES ('Chicken Doner', 5);
INSERT INTO menuItems (itemName, businessId) VALUES ('Lamb Doner', 5);
INSERT INTO menuItems (itemName, businessId) VALUES ('Vegetable Doner', 5);
INSERT INTO menuItems (itemName, businessId) VALUES ('Mixed Doner', 5);

-- Test Data for the 'customer' table
INSERT INTO customer (customerEmailAddress, customerPassword, customerPhoneNumber) VALUES ('alice.smith@gmail.com', '4t4ba4tb4ate4tae4tae', '+44 7346 764583');
INSERT INTO customer (customerEmailAddress, customerPassword, customerPhoneNumber) VALUES ('bobjones@hotmail.com', '6u6yuew5r6yw4b5tywbe', '+44 7845 567287');
INSERT INTO customer (customerEmailAddress, customerPassword, customerPhoneNumber) VALUES ('charliebrown@gmail.com', 'ae4tbae4tae4tbae4tae', '+44 7348 097662');
INSERT INTO customer (customerEmailAddress, customerPassword, customerPhoneNumber) VALUES ('david.williams@btinternet.com', 'tyuae4tbae4tbae4tae4', '+44 7856 345671');
INSERT INTO customer (customerEmailAddress, customerPassword, customerPhoneNumber) VALUES ('emilydavis@plusnet.org.uk', 'tas4btae4tae4tae4tae', '+44 7745 900405');

-- Test Data for the 'orders' table
INSERT INTO orders (customerId, businessId, orderTime) VALUES (1, 4, '2024-04-04 22:34:51');
INSERT INTO orders (customerId, businessId, orderTime) VALUES (1, 5, '2024-04-04 23:11:17');
INSERT INTO orders (customerId, businessId, orderTime) VALUES (1, 2, '2024-04-05 12:02:17');
INSERT INTO orders (customerId, businessId, orderTime) VALUES (2, 3, '2024-04-06 19:22:43');
INSERT INTO orders (customerId, businessId, orderTime) VALUES (2, 1, '2024-04-07 10:26:17');
INSERT INTO orders (customerId, businessId, orderTime) VALUES (3, 1, '2024-04-07 21:33:20');
INSERT INTO orders (customerId, businessId, orderTime) VALUES (3, 1, '2024-04-09 18:24:00');
INSERT INTO orders (customerId, businessId, orderTime) VALUES (4, 4, '2024-04-09 18:55:13');
INSERT INTO orders (customerId, businessId, orderTime) VALUES (5, 2, '2024-04-10 10:31:07');

-- Test Data for the 'orderItems' table
INSERT INTO orderItems (orderId, itemId) VALUES (1, 13);
INSERT INTO orderItems (orderId, itemId) VALUES (1, 13);
INSERT INTO orderItems (orderId, itemId) VALUES (1, 14);
INSERT INTO orderItems (orderId, itemId) VALUES (2, 18);
INSERT INTO orderItems (orderId, itemId) VALUES (3, 5);
INSERT INTO orderItems (orderId, itemId) VALUES (4, 11);
INSERT INTO orderItems (orderId, itemId) VALUES (5, 1);
INSERT INTO orderItems (orderId, itemId) VALUES (5, 2);
INSERT INTO orderItems (orderId, itemId) VALUES (6, 2);
INSERT INTO orderItems (orderId, itemId) VALUES (7, 3);
INSERT INTO orderItems (orderId, itemId) VALUES (7, 3);
INSERT INTO orderItems (orderId, itemId) VALUES (7, 1);
INSERT INTO orderItems (orderId, itemId) VALUES (7, 2);
INSERT INTO orderItems (orderId, itemId) VALUES (8, 14);
INSERT INTO orderItems (orderId, itemId) VALUES (8, 13);
INSERT INTO orderItems (orderId, itemId) VALUES (9, 7);
INSERT INTO orderItems (orderId, itemId) VALUES (9, 7);
INSERT INTO orderItems (orderId, itemId) VALUES (9, 5);