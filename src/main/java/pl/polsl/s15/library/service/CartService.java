package pl.polsl.s15.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.s15.library.commons.exceptions.reservations.BooksUnavailableException;
import pl.polsl.s15.library.commons.exceptions.reservations.NoCartException;
import pl.polsl.s15.library.commons.exceptions.reservations.NoSuchUserException;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;
import pl.polsl.s15.library.domain.reservations.Reservation;
import pl.polsl.s15.library.domain.stock.books.Book;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.domain.user.Client;
import pl.polsl.s15.library.dtos.reservations.OrderItemDTO;
import pl.polsl.s15.library.repository.CartRepository;
import pl.polsl.s15.library.repository.ClientRepository;
import pl.polsl.s15.library.repository.RentalBookRepository;
import pl.polsl.s15.library.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    public CartService(CartRepository cartRepository, RentalBookRepository rentalBookRepository, ClientRepository clientRepository, ReservationRepository reservationRepository) {
        this.cartRepository = cartRepository;
        this.rentalBookRepository = rentalBookRepository;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }
    private final CartRepository cartRepository;
    private final RentalBookRepository rentalBookRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;

    public Optional<Client> getClient(long clientID)
    {
        return clientRepository.findById(clientID);
    }
    public Cart getCart(long clientID)
    {
        return clientRepository.findById(clientID).orElseThrow(()->new NoSuchUserException(clientID)).getCart();
    }
    public Optional<RentalBook> getRentalBook(long bookID){return rentalBookRepository.findById(bookID);}
    @Transactional
    public void saveCart(Cart cart)
    {
        cartRepository.save(cart);
    }
    @Transactional
    public void addItem(Client client, OrderItemDTO itemRequest)
    {
        Cart cart = getCart(client.getId());
        if(cart == null)
        {
            cart = new Cart();
        }
        cart.addOrderItem(itemRequest.getOrderItem(cart));
        saveCart(cart);
    }
    @Transactional
    public void removeItem(Client client, OrderItemDTO itemRequest)
    {
        Cart cart = getCart(client.getId());
        if(cart == null)
        {
            throw new NoCartException(client.getId());
        }
        cart.removeOrderItem(itemRequest.getOrderItem(cart));
        saveCart(cart);
    }
    @Transactional
    public void updateItem(Client client, OrderItemDTO itemRequest)
    {
        Cart cart = getCart(client.getId());
        if(cart == null)
        {
            throw new NoCartException(client.getId());
        }
        cart.updateOrderItem(itemRequest.getOrderItem(cart));
        saveCart(cart);
    }
    private Long FindAndOccupyFreeRentalBook(long bookID, Client client, LocalDateTime end_time) {
        Optional<RentalBook> optRentalBook = rentalBookRepository.findById(bookID);
        if(optRentalBook.isEmpty())
            return 0L;
        Book rentalBook = optRentalBook.get();
        Long number = 0L;
        for (RentalBook rb : rentalBookRepository.findAllFreeByDetailsId(rentalBook.getDetails().getId())) {
                if (!rb.getIsOccupied()) {
                    number = rb.getId();
                    rb.Occupy();
                    rentalBookRepository.save(rb);
                    Reservation reservation = new Reservation(rb,client,end_time);
                    reservationRepository.save(reservation);
                    break;
                }
            }
        return number;
    }
    @Transactional
    public void submitCart(Cart cart)
    {
        List<Long> noFreeBooks = new ArrayList<>();
        boolean error = false;
        Iterator<OrderItem> i = cart.getOrderItems().iterator();
        while (i.hasNext()) {
            OrderItem item = i.next();
            Long freeBookID = FindAndOccupyFreeRentalBook(item.getItemId(),clientRepository.findClientByCartId(cart.getId()).orElseThrow(() -> new NoSuchUserException(cart.getId())),item.getRequestedEndDate());
            if(freeBookID==0) {
                error = true;
                noFreeBooks.add(item.getItemId());
                i.remove();
            }
        }
        if(error) {
            String unavailable = "";
            for (Long id : noFreeBooks) {
                unavailable += id.toString() + " , ";
            }
            cartRepository.saveAndFlush(cart);
            throw new BooksUnavailableException(unavailable);
        }
        cart.clearItems();
        saveCart(cart);
    }
}
