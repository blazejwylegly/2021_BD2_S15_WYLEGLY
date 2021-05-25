package pl.polsl.s15.library.controller.registration.dto;

import lombok.Builder;

import java.util.Date;

public class RegistrationSuccessfulResponseDTO extends RegistrationResponseDTO {

    private String username;
    private String emailAddress;

    @Builder
    private RegistrationSuccessfulResponseDTO(String message, Date timestamp, String username, String emailAddress) {
        super(message, timestamp);
        this.username = username;
        this.emailAddress = emailAddress;
    }
}
