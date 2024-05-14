# Final Java Project - Team Gel

## Description

This project is a food ordering application, where users order different foods from different restaurants. It was developed by Team Gel as a final project for C385 Java.

## Tech Stack

![image](https://github.com/Chlebab/Gleats/assets/140327423/881d6570-e72f-42d5-b84d-38e659e17981)

![image](https://github.com/Chlebab/Gleats/assets/140327423/044f7c27-2026-4f0f-99df-a58e8431674e)

## Features

- **Restaurant Listing**: Displays a list of available restaurants.
- **Menu Display**: Shows the menu items available for each restaurant.
- **Order Placement**: Allows users to place orders. This includes adding items to a cart, specifying quantities, and checking out.

## Functions

- **CRUD**: Fora all aspects of data manipulation there is a create, get all, get by id, update and delete by id
- **update[order/business/customer/menuItem]Data**: Updates the existing order a if the orderId is found and if not returns -1 for setOrderID and setItemId with a message saying could not be updated.

## Installation

To install and run this project, you will need Node.js and NPM installed. Here are the steps to get it set up:

1. Clone the repository: `git clone https://github.com/theacademy/final-java-project-team-gel`
2. using terminal Navigate to the project directory: `cd FrontGel`
3. npm i (Installing all the dependencies required)
4. Run the Schema.sql followed Data.sql in MYSQL Workbench (files located inside of resources simply copy and paste the code from there and will generate the schema necessary for the application)
5. Run the java file called "TakeawayApplication.java". This will run the back-end of the application
6. Go to terminal and make sure this is what is populating "final-java-project-team-gel\FrontGel" and in that terminal type "npm run dev". This will run the front-end of the application.

once it runs should look something like this:

> frontgel@0.0.0 dev
> vite

VITE v5.2.8 ready in 433 ms

➜ Local: http://localhost:5173/
➜ Network: use --host to expose
➜ press h + enter to show help

7. Go to a webrowser and type the link "http://localhost:5173/" and will load the front-end and allow you to use the application and enjoy.

## Usage

On starting the application it will display the current customers on the application.

# update a user

1. Click "Settings" by an email address. Opens a page to either update or delete a user.
2. In the page with url "http://localhost:5173/customer/options/1" will allow the user to make changes to the currently stored email address and phone number.
3. Simply enter a new email address or phone number to update or make no changes to keep the current information.
4. Press "Update Customer" to update the customer and will give a success message in green.

# delete a user

1. Click "Settings" by an email address. Opens a page to either update or delete a user.
2. In the page with url "http://localhost:5173/customer/options/1" will allow the user to make changes to the currently stored email address and phone number.
3. Simply press "Delete Customer" to delete the customer and will give a success message in green.

# Making an order

1. click the customer you want to make the order
2. Select the restaurant or business you want to eat from.
3. Select as many items as you want from this page by pressing "Add to Basket".
4. A brief summary of the items currently in basket will be displayed at the bottom of the page.
5. Then press "Submit order" to process the order.
6. You can also clear the basket by pressing "Clear Basket".
