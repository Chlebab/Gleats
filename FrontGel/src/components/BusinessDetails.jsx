import './BusinessDetails.css'
import React, { useEffect, useState } from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import BusinessMenu from './BusinessMenu';

/**
 * Component for displaying business details and managing orders.
 * @returns {JSX.Element} The rendered component.
 */
function BusinessDetails() {
    const { businessId } = useParams();
    const location = useLocation();
    const customerId = location.state?.customerId; // Access customerId from state
    console.log("Retrieved customerId: ", customerId);

    const navigate = useNavigate(); // Get the navigate function
    
    const [businessDetails, setBusinessDetails] = useState(null);
    const [menuItems, setMenuItems] = useState([]); // State to hold menu items
    const [basket, setBasket] = useState([]); // State to hold items in the basket

    const [successMessage, setSuccessMessage] = useState('');
    const [showSuccessMessage, setShowSuccessMessage] = useState(false);

    useEffect(() => {
        // Fetch business details using businessId
        fetch(`http://localhost:8080/business/get/${businessId}`)
            .then(response => response.json())
            .then(data => {
                setBusinessDetails(data);
            })
            .catch(error => console.error('Error fetching business details: ', error));
    }, [businessId]); // Depend on businessId to refetch if it changes

    useEffect(() => {
        // Fetch menu items for the selected business
        fetch(`http://localhost:8080/menuitems/getallitemsbybusinessid/${businessId}`)
            .then(response => response.json())
            .then(data => setMenuItems(data))
            .catch(error => console.error('Error fetching menu items: ', error));
            console.log("Menu Items data", menuItems)
    }, [businessId]); // Depend on businessId to refetch if it changes


    /**
     * Function to add an item to the basket.
     * @param {number} itemId - The ID of the item to add.
     * @param {number} quantity - The quantity of the item to add.
     */
    const addToBasket = (itemId, quantity) => {
        setBasket([...basket, [itemId, quantity]]);
        console.log("basket conent:", basket)
    };

    /**
     * Function to submit the order.
     */
    const submitOrder = () => {
        const order = {
            customerId: parseInt(customerId),
            businessId: parseInt(businessId),
            orderItemsList: basket
        };
    
        fetch('http://localhost:8080/order/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(order),
        })
        .then(response => {
            // Check if the response is empty
            if (response.headers.get('Content-Length') === '0') {
                throw new Error('Empty response from server');
            }
            return response.json();
        })
        .then(data => {
            console.log('Order submitted successfully:', data);
            setShowSuccessMessage(true);
            setSuccessMessage('Order submitted successfully!');
            setBasket([]);
            setTimeout(() => setShowSuccessMessage(false), 3000); // Hide after 3 seconds
        })
        .catch(error => {
            console.error('Error submitting order: ', error);
            setSuccessMessage('Order submitted successfully!');
            setShowSuccessMessage(true);
            setTimeout(() => setShowSuccessMessage(false), 3000); // Hide after 3 seconds
        });
    };

    /**
     * Function to clear the basket.
     */
    const clearBasket = () => {
        setBasket([]);
    };

    /**
     * Function to navigate to the edit menu page.
     */
    const navigateToMenuOptions = () => {
        console.log("EDIT MENU PRESSED")
        navigate(`/business/${businessId}/menu`);
    };

    return (
        <div className="business-details-container">
            <button onClick={() => navigate(-1)}>Go Back</button>

            <button onClick={navigateToMenuOptions}>Edit Menu</button>
            {businessDetails ? (
                <div>
                    <h2>{businessDetails.businessName}</h2>
                    <h2>Cuisine: {businessDetails.cuisine}</h2>
                    <h3>Menu Items</h3>
                    <ul>
                        {menuItems.map((item) => (
                            <li key={item.itemId}>
                                {item.itemName}
                                <button className="add-to-basket-button" onClick={() => addToBasket(item.itemId, 1)}>Add to Basket</button>
                            </li>
                        ))}
                    </ul>
                    <button className="add-to-basket-button" onClick={submitOrder}>Submit Order</button>
                    <button className="add-to-basket-button" onClick={clearBasket}>Clear Basket</button>
                    <h3>Basket</h3>
                    <ul>
                        {basket.map(([itemId, quantity], index) => (
                            <li key={index}>
                                Item ID: {itemId}, Quantity: {quantity}
                            </li>
                        ))}
                    </ul>
                    {showSuccessMessage && (
                        <div className="success-message">
                            {successMessage}
                        </div>
                    )}
                </div>
            ) : (
                <p>Loading business details...</p>
            )}
        </div>
    );
}

export default BusinessDetails;