package com.projects.sunrise_hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long bookingId;

    @Column(name = "check_in")
    private LocalDate checkInDate;


    @Column(name = "check_out")
    private  LocalDate checkOutDate;


    @Column(name = "guest_full_name")
    private String guestFullName;

    @Column(name = "guest_email")
    private  String guestEmail;



   @Column(name = "adults")
    private int NumOfChildren;


    @Column(name = "children")
    private int NumOfAdults;


   @Column(name = "total_guest")
    private int totalNumOfGuest;

   @Column(name = "confirmation_Code")
    private String bookingConfirmationCode;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "room_id")//to join the room to the current table
    private Room room;



    public void calculateTotalNumOfGuest(){

        this.totalNumOfGuest = this.NumOfAdults + this.NumOfChildren;
    }

    public void setNumOfChildren(int numOfChildren) {
        NumOfChildren = numOfChildren;
        calculateTotalNumOfGuest();

    }

    public void setNumOfAdults(int numOfAdults) {
        NumOfAdults = numOfAdults;
        calculateTotalNumOfGuest();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }


}
