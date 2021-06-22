package pl.polsl.s15.library.api.controller.user.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.dtos.users.UserDTO;

import java.util.Date;
import java.util.List;

@Getter
public class GetAllUsersResponse extends ResponseDTO {
    private List<UserDTO> users;

    @Builder(builderMethodName = "getUsersResponseBuilder")
    public GetAllUsersResponse(HttpStatus status,
                               String message,
                               Date timestamp,
                               List<UserDTO> users) {
        super(status, message, timestamp);
        this.users = users;
    }
}
