import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import './BusinessSelection.css'; // Import the CSS file

/**
 * Component for selecting a business/restaurant.
 * @returns {JSX.Element} The BusinessSelection component.
 */
function BusinessSelection() {
    const { customerId } = useParams();
    const [businesses, setBusinesses] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        // Fetch all businesses
        fetch('http://localhost:8080/business/getAll') 
        .then(response => response.json())
        .then((data) => {
            setBusinesses(data);
        })
        .catch(error => console.error('Error fetching business data: ', error));
    }, []);

    /**
     * Handles the selection of a business.
     * @param {Object} business - The selected business object.
     */
    const handleBusinessSelect = (business) => {
        const businessId = business.businessId;
        // Navigate to the business details page for the selected business
        navigate(`/business/${businessId}`, { state: { customerId: customerId } });
    };
        
    /**
     * Navigates to the customer add page.
     */
    const navigateToAddBusiness = () => {
        console.log("ADD NEW BUSINESS PRESSED")
        navigate('/business/add');
    };

    return (
        <div className="business-selection-container">
            <button className="go-back-button" onClick={() => navigate(-1)}>Go Back</button>
            <button className="add-business-button" onClick={navigateToAddBusiness}>Add New Business</button>
            <h1>Select a Restaurant</h1>
            <ul>
                {businesses.map((business) => (
                    <li key={business.businessId} onClick={() => handleBusinessSelect(business)}>
                        {business.businessName}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default BusinessSelection;