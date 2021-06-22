package pl.polsl.s15.library.api.controller.cart;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.polsl.s15.library.api.controller.cart.response.GetCartMetaDataResponse;
import pl.polsl.s15.library.dtos.reservations.meta.CartMetaData;

import java.util.Date;

@Component
public class CartReqRepMapper {
    public static GetCartMetaDataResponse getCartMetaDataResponse(CartMetaData cartMetaData) {
        return GetCartMetaDataResponse.getCartMetaDataResponseBuilder()
                .status(HttpStatus.OK)
                .message("Successfully retrieved cart meta data")
                .timestamp(new Date())
                .cartId(cartMetaData.getCartId())
                .itemsNumber(cartMetaData.getItemsNumber())
                .build();
    }
}
