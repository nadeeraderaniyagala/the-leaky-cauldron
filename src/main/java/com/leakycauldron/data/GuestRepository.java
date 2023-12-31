package com.leakycauldron.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    List<Guest> findGuestByEmailAddress(String emailAddress);

    List<Guest> deleteGuestByEmailAddress(String emailAddress);
}
