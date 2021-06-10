package pl.polsl.s15.library.api.response.permissions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import pl.polsl.s15.library.api.response.ResponseDTO;

import java.util.Date;
import java.util.Set;

@Data
public class RolesResponseDTO extends ResponseDTO {
    private Set<String> roles;

    @Builder(builderMethodName = "rolesResponseBuilder")
    public RolesResponseDTO(HttpStatus status,
                            String message,
                            Date timestamp,
                            Set<String> roles) {
        super(status, message, timestamp);
        this.roles = roles;
    }
}
