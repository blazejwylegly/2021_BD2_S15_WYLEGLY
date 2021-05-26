package pl.polsl.s15.library.controller.login.dto;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.s15.library.dtos.ResponseDTO;

@Getter
public class AuthResponseDTO extends ResponseDTO {

    private String accessToken;

    @Builder
    private AuthResponseDTO(String message, String accessToken) {
        super(message);
        this.accessToken = accessToken;
    }
}
