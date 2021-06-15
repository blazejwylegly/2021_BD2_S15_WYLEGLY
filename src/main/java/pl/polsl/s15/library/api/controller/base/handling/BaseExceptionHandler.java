package pl.polsl.s15.library.api.controller.base.handling;

import org.springframework.http.ResponseEntity;
import pl.polsl.s15.library.api.controller.base.response.ErrorResponseDTO;

public class BaseExceptionHandler {

    protected ResponseEntity<ErrorResponseDTO> prepareErrorResponse(ErrorResponseDTO responseDTO) {
        return ResponseEntity.status(responseDTO.getStatus())
                .body(responseDTO);
    }
}
