package pl.polsl.s15.library.controller.exception.handling;

import org.springframework.http.ResponseEntity;
import pl.polsl.s15.library.dtos.common.api.ErrorResponseDTO;

public class BaseExceptionHandler {

    protected ResponseEntity<ErrorResponseDTO> prepareErrorResponse(ErrorResponseDTO responseDTO) {
        return ResponseEntity.status(responseDTO.getStatus())
                .body(responseDTO);
    }
}
