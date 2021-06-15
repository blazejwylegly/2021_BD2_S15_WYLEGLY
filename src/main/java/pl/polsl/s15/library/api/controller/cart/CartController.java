package pl.polsl.s15.library.api.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.commons.exceptions.reservations.NoCartException;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.domain.user.Client;
import pl.polsl.s15.library.dtos.reservations.OrderItemDTO;
import pl.polsl.s15.library.dtos.reservations.OrderItemResponseDTO;
import pl.polsl.s15.library.service.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/get")
    List<OrderItemResponseDTO> getFullCart(@RequestParam(name = "clientID") long clientID)
    {
        Cart cart = getCart(clientID);
        List<OrderItemResponseDTO> response = new ArrayList<>();
        for(OrderItem item:cart.getOrderItems())
        {
            OrderItemResponseDTO orderItem = new OrderItemResponseDTO(item.getItemId(),item.getRequestedEndDate());
            Optional<RentalBook> optRentalBook = cartService.getRentalBook(item.getItemId());
            optRentalBook.ifPresent(orderItem::Fill);
            response.add(orderItem);
        }
        return response;
    }
    private Cart getCart(long clientID)
    {
        Cart cart = cartService.getCart(clientID);
        if(cart != null)
            return cart;
        else
            throw new NoCartException(clientID);
    }
    @PostMapping("/add")
    void addItem(@RequestParam(name = "clientID") long clientID,
                 @RequestBody OrderItemDTO orderItemRequest)
    {
        Client client = cartService.getClient(clientID);
        cartService.addItem(client, orderItemRequest);
    }
    @DeleteMapping("/delete")
    void deleteItem(@RequestParam(name = "clientID") long clientID,
                 @RequestBody OrderItemDTO orderItemRequest)
    {
        Client client = cartService.getClient(clientID);
        cartService.removeItem(client, orderItemRequest);
    }
    @PatchMapping("/update")
    void updateItem(@RequestParam(name = "clientID") long clientID,
                    @RequestBody OrderItemDTO orderItemRequest)
    {
        Client client = cartService.getClient(clientID);
        cartService.updateItem(client, orderItemRequest);
    }
    @PutMapping("/submit")
    void submitCart(@RequestParam(name = "clientID") long clientID)
    {
        cartService.submitCart(getCart(clientID));
    }



}
