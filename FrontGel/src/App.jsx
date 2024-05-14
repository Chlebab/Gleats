/**
 * Main application component.
 * Renders the application routes and components.
 *
 * @returns {JSX.Element} The rendered application component.
 */
import React from 'react';
import { BrowserRouter as Router, Route, Routes, useNavigate } from 'react-router-dom';
import TakeawayContainer from './containers/TakeawayContainer';
import BusinessDetails from './components/BusinessDetails';
import BusinessSelection from './components/BusinessSelection';
import AddCustomer from './components/AddCustomer';
import CustomerOptions from './components/CustomerOptions';
import AddBusiness from './components/AddBusiness';
import BusinessMenu from './components/BusinessMenu';

import './App.css'

function App() {
  return (
      <Router>
        <div className="App">
          <h1>GLEATS</h1>
          <Routes>
            <Route path="/" element={<TakeawayContainer />} />
            <Route path="/customer/:customerId" element={<BusinessSelection />} />
            <Route path="/business/:businessId" element={<BusinessDetails />} />
            <Route path="/customer/add" element={<AddCustomer />} />
            <Route path="/customer/options/:customerId" element={<CustomerOptions />} />
            <Route path="/business/add" element={<AddBusiness />} />
            <Route path="/business/:businessId/menu" element={<BusinessMenu />} />
          </Routes>
        </div>
      </Router>
  );
}

export default App;