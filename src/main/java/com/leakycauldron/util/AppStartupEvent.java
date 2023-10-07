package com.leakycauldron.util;

import com.leakycauldron.business.ReservationService;
import com.leakycauldron.business.RoomReservation;
import com.leakycauldron.data.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final DateUtil dateUtil;
    private final ReservationService reservationService;

    public AppStartupEvent(ReservationService reservationService, DateUtil dateUtil ) {
        this.reservationService= reservationService;
        this.dateUtil= dateUtil;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Date date = this.dateUtil.createDateFromDateString("2022-01-01");
        List<RoomReservation> reservations= this.reservationService.getRoomReservationsForDate(date);

        reservations.forEach(System.out::println);
    }
}
