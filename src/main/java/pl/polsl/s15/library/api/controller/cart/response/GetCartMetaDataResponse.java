package pl.polsl.s15.library.api.controller.cart.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;

import java.util.Date;

@Getter
@AllArgsConstructor
public class GetCartMetaDataResponse extends ResponseDTO {
    private Long cartId;
    private Integer itemsNumber;

    @Builder(builderMethodName = "getCartMetaDataResponseBuilder")
    public GetCartMetaDataResponse(HttpStatus status,
                                   String message,
                                   Date timestamp,
                                   Long cartId,
                                   int itemsNumber) {
        super(status, message, timestamp);
        this.cartId = cartId;
        this.itemsNumber = itemsNumber;
    }
}
