package com.leakycauldron.webservice;

import com.leakycauldron.business.ReservationService;
import com.leakycauldron.business.RoomReservation;
import com.leakycauldron.data.Guest;
import com.leakycauldron.data.Room;
import com.leakycauldron.util.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {

    private final DateUtil dateUtil;
    private final ReservationService reservationService;

    public WebserviceController(DateUtil dateUtil, ReservationService reservationService) {
        this.dateUtil = dateUtil;
        this.reservationService = reservationService;
    }

    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(String dateString){
        Date date = this.dateUtil.createDateFromDateString(dateString);
       return this.reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping(path="/guests")
    public List<Guest> getGuests(){
        return this.reservationService.getHotelGuests();
    }

    @GetMapping(path="/rooms")
    public List<Room> getRooms(){
        return this.reservationService.getHotelRooms();
    }

}
