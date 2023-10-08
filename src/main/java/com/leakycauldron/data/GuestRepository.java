package com.leakycauldron.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Iterable<Guest> findGuestByEmailAddress(String emailAddress);

    Iterable<Guest> deleteGuestByEmailAddress(String emailAddress);
}
