package pl.polsl.s15.library.controller.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class RegistrationResponse {
    protected String message;
    protected Date timestamp;
}
