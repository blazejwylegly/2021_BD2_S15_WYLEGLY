package pl.polsl.s15.library.api.controller.user.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.dtos.users.UserDTO;

import java.util.Date;

@Getter
public class GetUserResponse extends ResponseDTO {
    private UserDTO userDTO;

    @Builder(builderMethodName = "getUserResponseBuilder")
    public GetUserResponse(HttpStatus status,
                           String message,
                           Date timestamp,
                           UserDTO userDTO) {
        super(status, message, timestamp);
        this.userDTO = userDTO;
    }
}