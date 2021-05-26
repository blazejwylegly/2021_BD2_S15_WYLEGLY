package pl.polsl.s15.library.controller.registration.dto;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.dtos.ResponseDTO;

@Getter
public class RegistrationResponseDTO extends ResponseDTO {

    @Builder
    private RegistrationResponseDTO(String message) {
        super(message);
    }
}
