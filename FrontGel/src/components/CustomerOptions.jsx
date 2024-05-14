/**
 * Component for managing customer options.
 *
 * @component
 * @example
 * return (
 *   <CustomerOptions />
 * )
 */
import './CustomerOptions.css'
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

function CustomerOptions() {
    // Initialize state with empty strings to ensure controlled inputs
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');

    const [successMessage, setSuccessMessage] = useState(''); // State for success message
    const [errorMessage, setErrorMessage] = useState(''); // State for error message

    const { customerId } = useParams(); // Get customerId from URL params
    const navigate = useNavigate(); // Get the navigate function

    const [customer, setCustomer] = useState({}); // State to hold customer details

    // Fetch customer details on component mount
    useEffect(() => {
        fetch(`http://localhost:8080/customer/${customerId}`)
            .then(response => response.json())
            .then(data => {
                setCustomer(data);
                setEmail(data.customerEmailAddress); // Update email state
                setPhone(data.customerPhoneNumber); // Update phone state
            })
            .catch(error => {
                console.error('Error fetching customer data: ', error);
                setErrorMessage('Error fetching customer data.');
            });
    }, [customerId]);

    // Function to handle customer deletion

    const handleDelete = async () => {
        try {
            const response = await fetch(`http://localhost:8080/customer/delete/${customerId}`, {
                method: 'DELETE',
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            console.log('Customer deleted successfully');
            setSuccessMessage('Customer deleted successfully!');
        } catch (error) {
            console.error('Error deleting customer:', error);
            setErrorMessage('Error deleting customer.');
        }
    };

    // Function to handle customer update
    const handleUpdate = async (event) => {
        event.preventDefault();

        try {
            const response = await fetch(`http://localhost:8080/customer/update/${customerId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    customerEmailAddress: email,
                    customerPhoneNumber: phone,
                }),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            setSuccessMessage('Customer updated successfully!');
            console.log('Customer updated successfully');
        } catch (error) {
            console.error('Error updating customer:', error);
            setErrorMessage('Error updating customer.');
        }
    };

    return (
        <div className="customer-options-container">
        <button onClick={() => navigate('/')}>Go Back</button>
        <h1>Customer Options</h1>
        {successMessage && <p>{successMessage}</p>}
        {errorMessage && <p className="error">{errorMessage}</p>}
        <form onSubmit={handleUpdate}>
            <label htmlFor="email">Email Address:</label>
            <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
            />
            <br />
            <label htmlFor="phone">Phone Number:</label>
            <input
                type="tel"
                id="phone"
                value={phone}
                onChange={(e) => setPhone(e.target.value)}
                required
            />
            <br />
            <button type="submit">Update Customer</button>
        </form>
            <button onClick={handleDelete} className="delete-button">Delete Customer</button>
        </div>
    );
}

export default CustomerOptions;
