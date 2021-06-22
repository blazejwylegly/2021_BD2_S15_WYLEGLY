package pl.polsl.s15.library.api.controller.roles.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;
import pl.polsl.s15.library.domain.user.account.roles.RoleType;

import java.util.Date;
import java.util.Set;

@Getter
public class GetRolesResponseDTO extends ResponseDTO {

    Set<RoleType> roles;

    @Builder(builderMethodName = "getRolesResponseBuilder")
    public GetRolesResponseDTO(HttpStatus status,
                               String message,
                               Date timestamp,
                               Set<RoleType> roles) {
        super(status, message, timestamp);
        this.roles = roles;
    }
}
