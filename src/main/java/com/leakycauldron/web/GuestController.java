package com.leakycauldron.web;

import com.leakycauldron.business.ReservationService;
import com.leakycauldron.data.Guest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private final ReservationService reservationService;

    public GuestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(Model model) {
        model.addAttribute("guests", this.reservationService.getHotelGuests());
        return "hotel-guests"; //return "guests : circular reference"
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addGuests(Guest guest) {
        List<Guest> guestList = this.reservationService.getHotelGuests();
        if (Objects.isNull(guest.getEmailAddress())) {
            this.reservationService.addHotelGuest(guest);
            return "redirect:guests";
        }
        for (Guest g : guestList) {
            if (g.getEmailAddress().equalsIgnoreCase(guest.getEmailAddress())) {
                this.reservationService.deleteHotelGuestByEmail(g.getEmailAddress());
                this.reservationService.addHotelGuest(g);
                break;
            }
        }
        return "redirect:guests";
    }
}
