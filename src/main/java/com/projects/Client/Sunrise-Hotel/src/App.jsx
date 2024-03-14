import React from "react";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/bootstrap/dist/js/bootstrap.min.js";
import AddRoom from "./room/AddRoom.jsx";
import ExistingRooms from "./room/ExistingRooms.jsx";
import Home from "./home/Home.jsx";
import EditRoom from "./room/EditRoom.jsx";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import NavBar from "./layout/NavBar.jsx";
import Footer from "./layout/Footer.jsx";
import RoomListing from "./room/RoomListing.jsx";
import Admin from "./admin/Admin.jsx";
import Checkout from "./booking/Checkout.jsx";
import BookingSuccess from "./booking/BookingSuccess.jsx";
import Bookings from "./booking/Bookings.jsx";
import FindBooking from "./booking/FindBooking.jsx";
import Login from "./auth/Login.jsx";
import Registration from "./auth/Registration.jsx";
import Profile from "./auth/Profile.jsx";
import { AuthProvider } from "./auth/AuthProvider.jsx";
import RequireAuth from "./auth/RequireAuth.jsx";

function App() {
  return (
    <AuthProvider>
      <main>
        <Router>
          <NavBar></NavBar>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/edit-room/:roomId" element={<EditRoom />} />
            <Route path="/existing-rooms" element={<ExistingRooms />} />
            <Route path="/add-room" element={<AddRoom />} />

            <Route
              path="/book-room/:roomId"
              element={
                <RequireAuth>
                  <Checkout />
                </RequireAuth>
              }
            />
            <Route path="/browse-all-rooms" element={<RoomListing />} />

            <Route path="/admin" element={<Admin />} />
            <Route path="/booking-success" element={<BookingSuccess />} />
            <Route path="/existing-bookings" element={<Bookings />} />
            <Route path="/find-booking" element={<FindBooking />} />

            <Route path="/login" element={<Login />} />
            <Route path="login/register" element={<Registration />} />

            <Route path="/profile" element={<Profile />} />
            <Route path="/logout" element={<FindBooking />} />
          </Routes>
        </Router>
      </main>
    </AuthProvider>
  );
}

export default App;
