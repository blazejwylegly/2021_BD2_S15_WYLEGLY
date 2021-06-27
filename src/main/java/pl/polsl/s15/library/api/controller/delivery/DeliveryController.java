package pl.polsl.s15.library.api.controller.delivery;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.polsl.s15.library.dtos.delivery.DeliveryArticleDTO;
import pl.polsl.s15.library.dtos.delivery.DeliveryBasicDTO;
import pl.polsl.s15.library.dtos.delivery.DeliveryDTO;
import pl.polsl.s15.library.dtos.delivery.DeliveryFullDTO;
import pl.polsl.s15.library.service.DeliveryService;

import java.util.List;

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
    public void create(@RequestBody DeliveryDTO dto) {
        deliveryService.create(dto);
    }

    @Operation(summary = "Find all deliveries (basic info)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all deliveries"),
            @ApiResponse(code = 400, message = "Cannot return deliveries")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<DeliveryBasicDTO> findAll() {
        return deliveryService.findAll();
    }

    @Operation(summary = "Find delivery by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return delivery"),
            @ApiResponse(code = 400, message = "Cannot return delivery")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DeliveryFullDTO findById(@PathVariable Long id) {
        return deliveryService.findById(id);
    }

//    @Operation(summary = "Find delivery by id")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Return delivery"),
//            @ApiResponse(code = 400, message = "Cannot return delivery")
//    })
//    @PatchMapping("/{deliveryId}/update")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public DeliveryFullDTO updateDelivery(@RequestBody DeliveryArticleDTO articleDTO, @PathVariable Long deliveryId) {
//        deliveryService.update(articleDTO, deliveryId);
//        return deliveryService.findById(deliveryId);
//    }


}
