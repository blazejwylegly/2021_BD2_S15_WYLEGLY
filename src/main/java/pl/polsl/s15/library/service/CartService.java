package pl.polsl.s15.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.commons.enums.ReservationStatus;
import pl.polsl.s15.library.commons.exceptions.books.NoSuchBookException;
import pl.polsl.s15.library.commons.exceptions.reservations.*;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;
import pl.polsl.s15.library.domain.reservations.Reservation;
import pl.polsl.s15.library.domain.stock.books.Book;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.domain.user.Client;
import pl.polsl.s15.library.dtos.ordering.CartDTOMapper;
import pl.polsl.s15.library.dtos.ordering.OrderItemDTO;
import pl.polsl.s15.library.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    public CartService(CartRepository cartRepository, RentalBookRepository rentalBookRepository, ClientRepository clientRepository, ReservationRepository reservationRepository, OrderItemRepository orderItemRepository) {
        this.cartRepository = cartRepository;
        this.rentalBookRepository = rentalBookRepository;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
        this.orderItemRepository = orderItemRepository;
    }

    private final CartRepository cartRepository;
    private final RentalBookRepository rentalBookRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;
    private final OrderItemRepository orderItemRepository;

    public Client getClient(long clientID) {
        return clientRepository.findById(clientID).orElseThrow(() -> new NoSuchCartException(clientID));
    }

    public Cart getCartById(long cartId) {
        return cartRepository
                .findById(cartId)
                .orElseThrow(() -> new NoSuchCartException(cartId));
    }

    public Optional<RentalBook> getRentalBook(long bookID) {
        return rentalBookRepository.findById(bookID);
    }

    @Transactional
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Transactional
    public void addItem(Cart cart, OrderItemDTO itemRequest) {
        cart.addOrderItem(CartDTOMapper.toEntity(itemRequest));
        saveCart(cart);
    }

    @Transactional
    public void removeItem(Cart cart, Long itemId) {
        orderItemRepository.deleteByCartAndItemIds(cart.getId(),itemId);
        cart.removeOrderItem(itemId);
        saveCart(cart);
    }

    @Transactional
    public void updateItem(Cart cart, OrderItemDTO itemRequest) {
        cart.updateOrderItem(CartDTOMapper.toEntity(itemRequest));
        saveCart(cart);
    }

    private Long FindAndOccupyFreeRentalBook(long bookID, LocalDate end_time,Client client) {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findById(bookID);
        if (optRentalBook.isEmpty())
            return 0L;
        Book rentalBook = optRentalBook.get();
        Long number = 0L;
        for (RentalBook rb : rentalBookRepository.findAllFreeByDetailsId(rentalBook.getDetails().getId())) {
            if (!rb.getIsOccupied()) {
                number = rb.getId();
                rb.Occupy();
                rentalBookRepository.save(rb);
                Reservation reservation = new Reservation(rb, end_time);
                client.getReservations().add(reservation);
                reservationRepository.save(reservation);
                break;
            }
        }
        return number;
    }

    @Transactional
    public void submitCart(Cart cart) {
        List<Long> noFreeBooks = new ArrayList<>();
        boolean error = false;
        int i = cart.getOrderItems().size();
        Client client = clientRepository.findClientByCartId(cart.getId()).orElseThrow(()->new UnassignedCartException(cart.getId()));
        while (i>0) {
            OrderItem item = cart.getOrderItems().get(i-1);
            Long freeBookID = FindAndOccupyFreeRentalBook(item.getItemId(), item.getRequestedEndDate(),client);
            if (freeBookID.equals(0L)) {
                error = true;
                noFreeBooks.add(item.getItemId());
                //removeItem(cart, item.getItemId());
            }
            i--;
        }
        if (error) {
            StringBuilder unavailableBuilder = new StringBuilder();
            for (Long id : noFreeBooks) {
                unavailableBuilder.append(id.toString());
                unavailableBuilder.append(" , ");
            }
            String unavailable = unavailableBuilder.toString();
            throw new BooksUnavailableHelperException(unavailable,noFreeBooks);
        }
        clientRepository.save(client);
        orderItemRepository.deleteAllByCartId(cart.getId());
        cart.clearItems();
        saveCart(cart);
    }

    public List<Reservation> getReservations(long clientID) {
        return reservationRepository.findAllByClientId(clientID);
    }
    public List<Reservation> getAllPendingReservations()
    {
        return reservationRepository.findAllByStatus(ReservationStatus.PENDING);
    }
    @Transactional
    public void changeReservationStatus(long reservationID,ReservationStatus requiredStatus, ReservationStatus newStatus)
    {
        Optional<Reservation> optReservation = reservationRepository.findById(reservationID);
        Reservation reservation = optReservation.orElseThrow(()->new NoReservationException(reservationID));
        if(reservation.getStatus()!=requiredStatus)
            throw new ReservationAlreadyHandledException(reservationID);
        reservation.setStatus(newStatus);
        if(newStatus.equals(ReservationStatus.RETURNED))
            reservation.setReturned(true);
        reservationRepository.save(reservation);
    }
    public Reservation findReservationBySerialNumber(long serialNumber)
    {
        return reservationRepository.findByBookSerial(serialNumber).orElseThrow(()-> new NoReservedBookException(serialNumber));
    }
    @Transactional
    public void unlockReservationBook(long reservationID)
    {
        RentalBook rentalBook = reservationRepository.findById(reservationID).orElseThrow(()->new NoReservationException(reservationID)).getRentalBook();
        rentalBook.Free();
        rentalBookRepository.save(rentalBook);
    }
}
