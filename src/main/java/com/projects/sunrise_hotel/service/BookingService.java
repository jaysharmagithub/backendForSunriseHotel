package com.projects.sunrise_hotel.service;

import com.projects.sunrise_hotel.exception.InvalidBookingRequestException;
import com.projects.sunrise_hotel.exception.ResourceNotFoundException;
import com.projects.sunrise_hotel.model.BookedRoom;
import com.projects.sunrise_hotel.model.Room;
import com.projects.sunrise_hotel.repository.BookingRepository;
import com.projects.sunrise_hotel.response.BookingResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {
//    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
    private final BookingRepository bookingRespository;
    private final IRoomService roomService;

   @Override
    public List<BookedRoom> getAllBookings(){
        return bookingRespository.findAll();
    }

    @Override
    public List<BookedRoom> getBookingsByUserEmail(String email){
        return bookingRespository.findByGuestEmail(email);
    }

    @Override
    public void cancelBooking(Long bookingId){
        bookingRespository.deleteById(bookingId);
    }

    @Override
    public List<BookedRoom> getAllBookingsByRoomId(Long roomId){
        return null ;/*bookingRespository.findRoomById(roomId);*/
  }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())){
            throw new InvalidBookingRequestException("Check-in date must come before check-out date");
        }
        Room room = roomService.getRoomById(roomId).get();
        List<BookedRoom> existingBookings = room.getBookings();
        boolean roomIsAvailable = roomIsAvailable(bookingRequest,existingBookings);
        if (roomIsAvailable){
            room.addBooking(bookingRequest);
                                        bookingRespository.save(bookingRequest);
        }else{
            throw  new InvalidBookingRequestException("Sorry, This room is not available for the selected dates;");
        }
        return bookingRequest.getBookingConfirmationCode();
    }



    @Override
    public BookedRoom findByBookingConfirmationCode(String confirmationCode){
        return bookingRespository.findByBookingConfirmationCode(confirmationCode)
                .orElseThrow(() -> new ResourceNotFoundException("No booking found with booking code:"+ confirmationCode ));

    }



    private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                        || bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate())
                        || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                        && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                        || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                        && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                        || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                        && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

                        || bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate())
                        && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate())

                        || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                        && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))

                );
    }



}
