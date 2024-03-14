package com.projects.sunrise_hotel.repository;

import com.projects.sunrise_hotel.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



public interface BookingRepository extends JpaRepository<BookedRoom, Long> {
    List<BookedRoom> findRoomByRoomId(Long roomId);
    Optional<BookedRoom>    findByBookingConfirmationCode(String confirmationCode);

    List<BookedRoom> findByGuestEmail(String email);

}
