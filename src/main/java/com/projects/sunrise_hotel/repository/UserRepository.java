package com.projects.sunrise_hotel.repository;

import com.projects.sunrise_hotel.model.BookedRoom;
import com.projects.sunrise_hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
    Optional<User> findByEmail(String email);

}
