package pl.polsl.s15.library.api.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.api.controller.base.BaseController;
import pl.polsl.s15.library.api.controller.cart.response.GetCartMetaDataResponse;
import pl.polsl.s15.library.commons.exceptions.reservations.BooksUnavailableException;
import pl.polsl.s15.library.commons.exceptions.reservations.BooksUnavailableHelperException;
import pl.polsl.s15.library.domain.ordering.Cart;
import pl.polsl.s15.library.domain.ordering.OrderItem;
import pl.polsl.s15.library.domain.stock.books.RentalBook;
import pl.polsl.s15.library.dtos.ordering.OrderItemDTO;
import pl.polsl.s15.library.dtos.reservations.OrderItemResponseDTO;
import pl.polsl.s15.library.dtos.reservations.meta.CartMetaData;
import pl.polsl.s15.library.service.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController extends BaseController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/meta")
    public ResponseEntity<GetCartMetaDataResponse> getCartMetaData() {
        Authentication user = getCurrentUser();
        CartMetaData cartMetaData = cartService.prepareCartMetaData((String) user.getPrincipal());
        return ResponseEntity.ok()
                .body(CartReqRepMapper.getCartMetaDataResponse(cartMetaData));
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
    void addItem(@RequestParam(name = "cartId") long cartId,
                 @RequestBody OrderItemDTO orderItemRequest) {
        cartService.addItem(cartService.getCartById(cartId), orderItemRequest);
    }

    @DeleteMapping("/delete")
    void deleteItem(@RequestParam(name = "cartId") long cartId,
                    @RequestBody OrderItemDTO orderItemRequest) {
        cartService.removeItem(cartService.getCartById(cartId), orderItemRequest.getItemId());
    }

    @PatchMapping("/update")
    void updateItem(@RequestParam(name = "cartId") long cartId,
                    @RequestBody OrderItemDTO orderItemRequest) {
        cartService.updateItem(cartService.getCartById(cartId), orderItemRequest);
    }

    @PutMapping("/submit")
    void submitCart(@RequestParam(name = "cartId") long cartId) {
        try {
            cartService.submitCart(getCartById(cartId));
        } catch (BooksUnavailableHelperException e) {
            Cart cart = cartService.getCartById(cartId);
            for (int i = e.getIds().size(); i > 0; i--)
                cartService.removeItem(cart, e.getIds().get(i - 1));
            cartService.saveCart(cart);
            throw new BooksUnavailableException(e.getMessage());
        }
    }


}
