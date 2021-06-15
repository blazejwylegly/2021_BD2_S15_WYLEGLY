package pl.polsl.s15.library.api.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.api.controller.base.BaseController;
import pl.polsl.s15.library.api.controller.cart.response.GetCartMetaDataResponse;
import pl.polsl.s15.library.commons.exceptions.reservations.NoCartException;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.domain.user.Client;
import pl.polsl.s15.library.dtos.ordering.OrderItemDTO;
import pl.polsl.s15.library.dtos.reservations.OrderItemResponseDTO;
import pl.polsl.s15.library.dtos.reservations.meta.CartMetaData;
import pl.polsl.s15.library.service.CartService;
import pl.polsl.s15.library.service.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController extends BaseController {
    private CartService cartService;
    private ClientService clientService;

    @Autowired
    public CartController(CartService cartService, ClientService clientService) {
        this.cartService = cartService;
        this.clientService = clientService;
    }

    @GetMapping("/meta")
    public ResponseEntity<GetCartMetaDataResponse> getCartMetaData() {
        Authentication user = getCurrentUser();
        CartMetaData cartMetaData = prepareCartMetaData((String) user.getPrincipal());
        return ResponseEntity.ok()
                .body(CartReqRepMapper.getCartMetaDataResponse(cartMetaData));
    }

    private CartMetaData prepareCartMetaData(String username) {
        Client client = (Client) clientService.loadUserByUsername(username);
        int numItems = client.getNumItemsInCart();
        Long cartId = client.getCartId();
        return new CartMetaData(cartId, numItems);
    }

    @GetMapping("/get")
    List<OrderItemResponseDTO> getFullCart(@RequestParam(name = "cartId") long cartId) {
        Cart cart = getCartById(cartId);
        List<OrderItemResponseDTO> response = new ArrayList<>();
        for (OrderItem item : cart.getOrderItems()) {
            OrderItemResponseDTO orderItem = new OrderItemResponseDTO(item.getItemId(), item.getRequestedEndDate());
            Optional<RentalBook> optRentalBook = cartService.getRentalBook(item.getItemId());
            optRentalBook.ifPresent(orderItem::Fill);
            response.add(orderItem);
        }
        return response;
    }

    private Cart getCartById(long cartId) {
        return cartService.getCartById(cartId);
    }

    @PostMapping("/add")
    void addItem(@RequestParam(name = "cartId") long cartID,
                 @RequestBody OrderItemDTO orderItemRequest) {
        cartService.addItem(cartService.getCartById(cartID), orderItemRequest);
    }

    @DeleteMapping("/delete")
    void deleteItem(@RequestParam(name = "cartId") long cartID,
                    @RequestBody OrderItemDTO orderItemRequest) {
        cartService.removeItem(cartService.getCartById(cartID), orderItemRequest);
    }

    @PatchMapping("/update")
    void updateItem(@RequestParam(name = "cartID") long cartID,
                    @RequestBody OrderItemDTO orderItemRequest) {
        cartService.updateItem(cartService.getCartById(cartID), orderItemRequest);
    }

    @PutMapping("/submit")
    void submitCart(@RequestParam(name = "cartID") long cartID) {
        cartService.submitCart(getCartById(cartID));
    }


}
