import './AddCustomer.css'
import React, { useState } from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';

/**
 * Component for adding a new customer.
 * @returns {JSX.Element} The AddCustomer component.
 */
function AddCustomer() {
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [successMessage, setSuccessMessage] = useState(''); // State for success message

    const navigate = useNavigate(); // Get the navigate function

    /**
     * Handles the form submission.
     * @param {Event} event - The form submission event.
     */
    const handleSubmit = async (event) => {
        event.preventDefault(); // Prevent the default form submission

        const customerData = {
            customerEmailAddress: email,
            customerPhoneNumber: phone,
        };

        try {
            const response = await fetch('http://localhost:8080/customer/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(customerData),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const data = await response.json();
            console.log('Customer added successfully:', data);
            setSuccessMessage('Customer added successfully!'); // Set success message
            setEmail(''); // Clear email field
            setPhone(''); // Clear phone field
        } catch (error) {
            console.error('Error adding customer:', error);
        }
    };

    return (
        <div className="add-customer-container">
            <button className="button" onClick={() => navigate(-1)}>Go Back</button>
            <h1>Add New Customer</h1>
            {successMessage && <p>{successMessage}</p>} 
            <form onSubmit={handleSubmit}>
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
                <button className="button" type="submit">Add Customer</button>
            </form>
        </div>
    );
}

export default AddCustomer;