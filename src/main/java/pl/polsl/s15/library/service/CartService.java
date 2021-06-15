package pl.polsl.s15.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.commons.exceptions.reservations.BooksUnavailableException;
import pl.polsl.s15.library.commons.exceptions.reservations.NoSuchCartException;
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
    public CartService(CartRepository cartRepository, RentalBookRepository rentalBookRepository, ClientRepository clientRepository, ReservationRepository reservationRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.rentalBookRepository = rentalBookRepository;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }

    private final CartRepository cartRepository;
    private final RentalBookRepository rentalBookRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;

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
    public void removeItem(Cart cart, OrderItemDTO itemRequest) {
        cart.removeOrderItem(CartDTOMapper.toEntity(itemRequest));
        saveCart(cart);
    }

    @Transactional
    public void updateItem(Cart cart, OrderItemDTO itemRequest) {
        cart.updateOrderItem(CartDTOMapper.toEntity(itemRequest));
        saveCart(cart);
    }

    private Long FindAndOccupyFreeRentalBook(long bookID, LocalDate end_time) {
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
        Iterator<OrderItem> i = cart.getOrderItems().iterator();
        while (i.hasNext()) {
            OrderItem item = i.next();
            Long freeBookID = FindAndOccupyFreeRentalBook(item.getItemId(), item.getRequestedEndDate());
            if (freeBookID == 0) {
                error = true;
                noFreeBooks.add(item.getItemId());
                i.remove();
            }
        }
        if (error) {
            StringBuilder unavailableBuilder = new StringBuilder();
            for (Long id : noFreeBooks) {
                unavailableBuilder.append(id.toString());
                unavailableBuilder.append(" , ");
            }
            String unavailable = unavailableBuilder.toString();
            cartRepository.saveAndFlush(cart);
            throw new BooksUnavailableException(unavailable);
        }
        cart.clearItems();
        saveCart(cart);
    }

    public List<Reservation> getReservations(long clientID) {
        Client client = getClient(clientID);
        return reservationRepository.findAllByClientId(clientID);
    }
}
