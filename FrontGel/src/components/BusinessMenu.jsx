import './BusinessMenu.css';
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

/**
 * Component for displaying and managing a business menu.
 */
function BusinessMenu() {
    const [menuItems, setMenuItems] = useState([]); // State to hold the menu items
    const [newItemName, setNewItemName] = useState(''); // State to hold the name of the new item

    const { businessId } = useParams(); // Get the businessId from the URL parameters
    const navigate = useNavigate(); // Get the navigate function from react-router-dom

    /**
     * Fetches the menu items from the server based on the businessId.
     */
    const fetchMenuItems = () => {
        fetch(`http://localhost:8080/menuitems/getallitemsbybusinessid/${businessId}`)
            .then(response => response.json())
            .then(data => setMenuItems(data))
            .catch(error => console.error('Error fetching menu items: ', error));
    };

    useEffect(() => {
        if (businessId) {
            fetchMenuItems();
        }
    }, [businessId]);

    /**
     * Handles adding a new menu item.
     */
    const handleAddMenuItem = () => {
        const newMenuItem = {
            itemName : newItemName
        };
        setMenuItems([...menuItems, newMenuItem]);
        addMenuItemToDatabase(newItemName, businessId);
        setNewItemName('');
    };

    /**
     * Adds a new menu item to the database.
     * @param {string} itemName - The name of the new menu item.
     * @param {string} businessId - The ID of the business.
     */
    const addMenuItemToDatabase = (itemName, businessId) => {
        fetch('http://localhost:8080/menuitems/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ itemName, businessId }),
        })
        .then(response => response.json())
        .then(data => {
            console.log('Item added successfully:', data);
        })
        .catch(error => console.error('Error adding item:', error));
    };

    /**
     * Handles editing a menu item.
     * @param {string} id - The ID of the menu item.
     * @param {string} field - The field to be edited (e.g., 'itemName').
     * @param {string} value - The new value for the field.
     */
    const handleEditMenuItem = (id, field, value) => {
        const parsedId = parseInt(id, 10); // Convert id to a number
        const updatedMenuItems = menuItems.map(item => {
            if (item.id === id) { // Ensure this matches the property name used in your items
                return { ...item, [field]: value };
            }
            return item;
        });
        setMenuItems(updatedMenuItems);
        editMenuItemInDatabase(parsedId, value);
    };

    /**
     * Updates a menu item in the database.
     * @param {number} id - The ID of the menu item.
     * @param {string} itemName - The updated name of the menu item.
     */
    const editMenuItemInDatabase = (id, itemName) => {
        fetch(`http://localhost:8080/menuitems/update/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ itemName }), // Only send itemName, not businessId
        })
        .then(response => response.json())
        .then(data => {
            console.log('Item updated successfully:', data);
        })
        .catch(error => console.error('Error updating item:', error));
    };

    /**
     * Deletes a menu item from the database.
     * @param {string} id - The ID of the menu item to be deleted.
     */
    const deleteMenuItemFromDatabase = (id) => {
        fetch(`http://localhost:8080/menuitems/delete/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            console.log('Item deleted successfully');
        })
        .catch(error => console.error('Error deleting item:', error));
    };

    /**
     * Handles deleting a menu item.
     * @param {string} itemId - The ID of the menu item to be deleted.
     */
    const handleDeleteMenuItem = (itemId) => {
        deleteMenuItemFromDatabase(itemId);
        const updatedMenuItems = menuItems.filter(item => item.itemId !== itemId);
        setMenuItems(updatedMenuItems);
    };

    return (
        <div className="business-menu-container">
            <h2>Menu</h2>
            <div className="button-group">
                <button onClick={() => navigate(-1)}>Go Back</button>
            </div>
            <div className="input-group">
            <input
                type="text"
                value={newItemName}
                onChange={(e) => setNewItemName(e.target.value)}
                placeholder="New item name"
                className="input"
            />
            <button onClick={handleAddMenuItem} className="button">Add New Menu Item</button>
        </div>
            <h2>Items:</h2>
            {menuItems.map((item, index) => (
                    <div className="menu-item" key={index}>
                        <input
                        type="text"
                        value={item.itemName}
                        onChange={(e) => handleEditMenuItem(item.itemId, 'itemName', e.target.value)}
                    />
                    <button onClick={() => handleDeleteMenuItem(item.itemId)}>Delete</button>
                </div>
            ))}
        </div>
    );
}

export default BusinessMenu;