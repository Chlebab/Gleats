DROP DATABASE IF EXISTS takeaway;

create database takeaway;
use takeaway;

-- Table structure for Business

DROP TABLE IF EXISTS business;
CREATE TABLE business (
	businessId int AUTO_INCREMENT PRIMARY KEY,
	businessName varchar(255) DEFAULT NULL,
	cuisine varchar(255) DEFAULT NULL
);

-- Table structure for MenuItems

DROP TABLE IF EXISTS menuItems;
CREATE TABLE menuItems (
	itemId int AUTO_INCREMENT PRIMARY KEY,
	itemName varchar(255) DEFAULT NULL,
	businessId int NOT NULL,
	foreign key fk_menuItems_businessId (businessId)
		references business(businessId)
);

-- Table structure for Customer

DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
	customerId int AUTO_INCREMENT PRIMARY KEY,
    customerEmailAddress varchar(255) NOT NULL,
    customerPassword varchar(255) NOT NULL,
	customerPhoneNumber varchar(255) DEFAULT NULL
);

-- Table structure for Order

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
	orderId int AUTO_INCREMENT PRIMARY KEY,
	customerId int NOT NULL,
    businessId int NOT NULL,
    foreign key fk_orders_customerId (customerId)
		references customer(customerId),
	foreign key fk_orders_businessId (businessId)
		references business(businessId)
);

-- Table structure for OrderItems

DROP TABLE IF EXISTS orderItems;
CREATE TABLE orderItems (
	orderId int not null,
    itemId int not null,
    primary key (orderId, itemId),
    foreign key fk_orderItems_orderId (orderId)
		references orders(orderId),
	foreign key fk_orderItems_itemId (itemId)
		references menuItems(itemId)
);
