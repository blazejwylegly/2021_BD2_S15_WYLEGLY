package pl.polsl.s15.library.api.controller.reservations;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.commons.enums.ReservationStatus;
import pl.polsl.s15.library.domain.reservations.Reservation;
import pl.polsl.s15.library.dtos.reservations.ReservationDTO;
import pl.polsl.s15.library.service.CartService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.time.LocalDate;
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
    List<ReservationDTO> getReservations(@RequestParam(name = "clientId") long clientId)
    {
        List<Reservation> reservations = cartService.getReservations(clientId);
        List<ReservationDTO> response = new ArrayList<>();
        for(Reservation item:reservations)
        {
            response.add(new ReservationDTO(item.getId(),item.getEndTime(),item.getRentalBook(),item.getReturned(),item.getStatus()));
        }
        return response;
    }
    @GetMapping("/all")
    List<ReservationDTO> getAllReservations()
    {
        List<Reservation> reservations = cartService.getAllReservations();
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
    void acceptReservation(@RequestParam(name = "reservationId") long reservationId)
    {
        cartService.changeReservationStatus(reservationId,ReservationStatus.PENDING,ReservationStatus.ACCEPTED);
    }
    @PostMapping("/reject")
    void rejectReservation(@RequestParam(name = "reservationId") long reservationId)
    {
        cartService.changeReservationStatus(reservationId,ReservationStatus.PENDING,ReservationStatus.REJECTED);
        cartService.unlockReservationBook(reservationId);
    }
    @PostMapping("/lend")
    void lendReservedBook(@RequestParam(name = "serialNumber") long serialNumber)
    {
        long reservationId = cartService.findReservationBySerialNumber(serialNumber).getId();
        cartService.changeReservationStatus(reservationId,ReservationStatus.ACCEPTED,ReservationStatus.TAKEN);
    }
    @PostMapping("/return")
    void returnReservedBook(@RequestParam(name = "serialNumber") long serialNumber)
    {
        Reservation reservation = cartService.findReservationBySerialNumber(serialNumber);
        long reservationId = reservation.getId();
        cartService.changeReservationStatus(reservationId,ReservationStatus.TAKEN,ReservationStatus.RETURNED);
        cartService.unlockReservationBook(reservationId);
    }
    @RequestMapping(value = "/report",method = RequestMethod.GET)
    @ResponseBody byte[] getReport(@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                             @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)//,
                             //HttpServletResponse response)
    {
        return cartService.getReport(startDate,endDate);//,response);
    }
}
