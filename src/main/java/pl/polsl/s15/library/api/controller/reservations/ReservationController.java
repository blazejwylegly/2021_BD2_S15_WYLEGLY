package pl.polsl.s15.library.api.controller.reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.commons.enums.ReservationStatus;
import pl.polsl.s15.library.domain.reservations.Reservation;
import pl.polsl.s15.library.dtos.reservations.ReservationDTO;
import pl.polsl.s15.library.service.CartService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private CartService cartService;

    @Autowired
    public ReservationController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get")
    List<ReservationDTO> getReservations(@RequestParam(name = "clientID") long clientID)
    {
        List<Reservation> reservations = cartService.getReservations(clientID);
        List<ReservationDTO> response = new ArrayList<>();
        for(Reservation item:reservations)
        {
            response.add(new ReservationDTO(item.getId(),item.getEndTime(),item.getRentalBook(),item.getReturned(),item.getStatus()));
        }
        return response;
    }
    @GetMapping("/pending")
    List<ReservationDTO> getPendingReservations() {
        List<Reservation> reservations = cartService.getAllPendingReservations();
        List<ReservationDTO> response = new ArrayList<>();
        for (Reservation item : reservations) {
            if (item.getStatus().equals(ReservationStatus.PENDING))
                response.add(new ReservationDTO(item.getId(), item.getEndTime(), item.getRentalBook(), item.getReturned(), item.getStatus()));
        }
        return response;
    }
    @PostMapping("/accept")
    void acceptReservation(@RequestParam(name = "reservationID") long reservationID)
    {
        cartService.changeReservationStatus(reservationID,ReservationStatus.PENDING,ReservationStatus.ACCEPTED);
    }
    @PostMapping("/reject")
    void rejectReservation(@RequestParam(name = "reservationID") long reservationID)
    {
        cartService.changeReservationStatus(reservationID,ReservationStatus.PENDING,ReservationStatus.REJECTED);
        cartService.unlockReservationBook(reservationID);
    }
    @PostMapping("/lend")
    void lendReservedBook(@RequestParam(name = "serialNumber") long serialNumber)
    {
        long reservationID = cartService.findReservationBySerialNumber(serialNumber).getId();
        cartService.changeReservationStatus(reservationID,ReservationStatus.ACCEPTED,ReservationStatus.TAKEN);
    }
    @PostMapping("/return")
    void returnReservedBook(@RequestParam(name = "serialNumber") long serialNumber)
    {
        Reservation reservation = cartService.findReservationBySerialNumber(serialNumber);
        long reservationID = reservation.getId();
        cartService.changeReservationStatus(reservationID,ReservationStatus.TAKEN,ReservationStatus.RETURNED);
        cartService.unlockReservationBook(reservationID);
    }
}
