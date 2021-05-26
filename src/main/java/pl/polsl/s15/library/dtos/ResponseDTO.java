package pl.polsl.s15.library.dtos;

import lombok.Getter;

import java.util.Date;

@Getter
public abstract class ResponseDTO {
    private String message;
    private Date timestamp;

    public ResponseDTO(String message) {
        this.message = message;
        timestamp = new Date();
    }
}