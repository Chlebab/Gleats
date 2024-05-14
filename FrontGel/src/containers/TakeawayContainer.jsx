import './TakeawayContainer.css'; 
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function TakeawayContainer() {
    const [customers, setCustomers] = useState([]);
    const [selectedCustomer, setSelectedCustomer] = useState(null);
    const [customerSelected, setCustomerSelected] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        fetch('http://localhost:8080/customer/customers')
            .then(response => response.json())
            .then(data => setCustomers(data))
            .catch(error => console.error('Error fetching customer data: ', error));
    }, []);
    
    const handleCustomerSelect = (customer) => {
        console.log("SELECT THE RESTAURANT PRESSED")
        setSelectedCustomer(customer.customerId);
        setCustomerSelected(true);
        console.log("Selected customerID: ", customer.customerId)
        navigate(`/customer/${customer.customerId}`, { state: { customerId: customer.customerId } });
    };

    // Function to navigate to the customer add page
    const navigateToAddCustomer = () => {
        console.log("ADD NEW CUSTOMER PRESSED")
        navigate('/customer/add');
    };

    // Function to navigate to the customer options page
    const navigateToCustomerOptions = (customerId) => {
        console.log("SETTINGS PRESSED")
        navigate(`/customer/options/${customerId}`);
    };

    return (
        <div className="TakeawayContainer">
            {!customerSelected && (
                <>
                    <h2>Select a customer to make an order</h2>
                    <button className="add-customer-button" onClick={navigateToAddCustomer}>Add New Customer</button>
                    <ol>
                        {customers.map((customer) => (
                            <li key={customer.customerId}>
                                <div className="email-container">
                                    {customer.customerEmailAddress}
                                </div>
                                <div className="button-container">
                                    <button onClick={() => handleCustomerSelect(customer)}>Select the customer</button>
                                    <button onClick={(event) => {
                                        event.stopPropagation();
                                        navigateToCustomerOptions(customer.customerId);
                                    }}>Settings</button>
                                </div>
                            </li>
                        ))}
                    </ol>
                </>
            )}
        </div>
    );
}


export default TakeawayContainer;