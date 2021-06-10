package pl.polsl.s15.library.api.controller.delivery;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.polsl.s15.library.commons.exceptions.delivery.DeliveryCantBeCreatedException;
import pl.polsl.s15.library.commons.exceptions.delivery.DeliveryNotFoundException;

@RestControllerAdvice
public class DeliveryExceptionHandler {
    @ExceptionHandler(DeliveryCantBeCreatedException.class)
    public ResponseEntity<String> handleDeliveryCantBeCreatedException(DeliveryCantBeCreatedException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(DeliveryNotFoundException.class)
    public ResponseEntity<String> handleDeliveryNotFoundException(DeliveryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
