package pl.polsl.s15.library.api.controller.reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
            response.add(new ReservationDTO(item.getId(),item.getEndTime(),item.getRentalBook(),item.getReturned()));
        }
        return response;
    }
}
