import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

/**
 * Component for adding a new business.
 * @returns {JSX.Element} The AddBusiness component.
 */
function AddBusiness() {
    const [businessName, setBusinessName] = useState('');
    const [cuisine, setCuisine] = useState('');
    const [successMessage, setSuccessMessage] = useState(''); // State for success message

    const navigate = useNavigate(); // Get the navigate function

    /**
     * Handles the form submission.
     * @param {Event} event - The form submission event.
     */
    const handleSubmit = async (event) => {
        event.preventDefault(); // Prevent the default form submission

        const businessData = {
            businessName: businessName,
            cuisine: cuisine,
        };

        try {
            const response = await fetch('http://localhost:8080/business/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(businessData),
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const data = await response.json();
            console.log('Business added successfully:', data);
            setSuccessMessage('Business added successfully!'); // Set success message
            setBusinessName(''); // Clear business name field
            setCuisine(''); // Clear cuisine field
        } catch (error) {
            console.error('Error adding business:', error);
        }
    };

    return (
        <div className="add-business-container">
            <button className="button" onClick={() => navigate(-1)}>Go Back</button>
            <h1>Add New Business</h1>
            {successMessage && <p>{successMessage}</p>} 
            <form onSubmit={handleSubmit}>
                <label htmlFor="businessName">Business Name:</label>
                <input
                    type="text"
                    id="businessName"
                    value={businessName}
                    onChange={(e) => setBusinessName(e.target.value)}
                    required
                />
                <br />
                <label htmlFor="cuisine">Cuisine:</label>
                <input
                    type="text"
                    id="cuisine"
                    value={cuisine}
                    onChange={(e) => setCuisine(e.target.value)}
                    required
                />
                <br />
                <button className="button" type="submit">Add Business</button>
            </form>
        </div>
    );
}

export default AddBusiness;
