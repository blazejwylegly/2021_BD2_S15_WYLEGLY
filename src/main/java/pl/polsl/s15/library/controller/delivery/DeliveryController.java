package pl.polsl.s15.library.controller.delivery;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.dtos.delivery.DeliveryDTO;
import pl.polsl.s15.library.service.DeliveryService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Operation(summary = "Create new delivery")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delivery created"),
            @ApiResponse(code = 400, message = "Cannot create delivery")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody DeliveryDTO dto){
        deliveryService.create(dto);
    }

}
