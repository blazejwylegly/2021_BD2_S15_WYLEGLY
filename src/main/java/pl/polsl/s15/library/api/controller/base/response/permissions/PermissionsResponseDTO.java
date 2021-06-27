package pl.polsl.s15.library.api.controller.base.response.permissions;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.controller.base.response.ResponseDTO;

import java.util.Date;
import java.util.Set;

public class PermissionsResponseDTO extends ResponseDTO {

    private Set<String> authorities;
    private Set<String> roles;

    @Builder(builderMethodName = "permissionsResponseBuilder")
    public PermissionsResponseDTO(HttpStatus status,
                                  String message,
                                  Date timestamp,
                                  Set<String> authorities,
                                  Set<String> roles) {
        super(status, message, timestamp);
        this.authorities = authorities;
        this.roles = roles;
    }
}
